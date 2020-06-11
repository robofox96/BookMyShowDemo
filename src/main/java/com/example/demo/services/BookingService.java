package com.example.demo.services;

import com.example.demo.apimodels.BookingRequestModel;
import com.example.demo.entities.BookingRequest;
import com.example.demo.entities.MovieSchedule;
import com.example.demo.entities.Seat;
import com.example.demo.entities.User;
import com.example.demo.repositories.BookingRequestRepo;
import com.example.demo.repositories.MovieScheduleRepo;
import com.example.demo.repositories.SeatRepo;
import com.example.demo.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BookingService {

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MovieScheduleRepo movieScheduleRepo;

    @Autowired
    BookingRequestRepo bookingRequestRepo;

    public Boolean lockSeats(List<Integer> seatIdList) throws InterruptedException {
        List<Seat> updatedSeatList = new ArrayList<>();
        for(Integer seatId : seatIdList){
            Seat seat = seatRepo.findById(seatId).orElse(null);
            if(Objects.nonNull(seat)){
                if(!seat.getStatus().equals(Seat.Status.EMPTY)){
                    throw new RuntimeException("Seat is not available. SeatId : " + seatId);
                }else{
                    seat.setStatus(Seat.Status.LOCKED);
                    updatedSeatList.add(seat);
                }
            }else{
                throw new RuntimeException("Invalid Seat Id --> "+seatId);
            }
        }
        seatRepo.saveAll(updatedSeatList);
        unlockSeats(updatedSeatList);
        return true;
    }

    @Async
    public void unlockSeats(List<Seat> seatList) throws InterruptedException {
        log.info("Starting Timer for locked seats");
        Thread.sleep(600000);
        try{
            for(Seat seat : seatList){
                if(!seat.getStatus().equals(Seat.Status.BOOKED)){
                    seat.setStatus(Seat.Status.EMPTY);
                    seatRepo.save(seat);
                }
            }
        }catch (Exception e){
            log.error("Error while unlocking seats", e);
        }
    }

    public Boolean bookSeats(BookingRequestModel bookingRequestModel){
        User user = userRepo.findById(bookingRequestModel.getUserId()).orElse(null);
        MovieSchedule movieSchedule = movieScheduleRepo.findById(bookingRequestModel.getMovieScheduleId()).orElse(null);
        if(Objects.isNull(user) || Objects.isNull(movieSchedule))
            throw new RuntimeException("Invalid user/moviechedule id");
        List<Seat> updatedSeatList = new ArrayList<>();
        for(Integer seatId : bookingRequestModel.getSeatIdList()){
            Seat seat = seatRepo.findById(seatId).orElse(null);
            if(Objects.isNull(seat)){
                throw new RuntimeException("Invalid seat id : " + seatId);
            }
            if(!seat.getStatus().equals(Seat.Status.LOCKED)){
                throw new RuntimeException("Error while booking seat ID : " + seatId);
            }
            seat.setStatus(Seat.Status.BOOKED);
            updatedSeatList.add(seat);
        }
        //All seats are available
        for(Seat seat : updatedSeatList){
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setUser(user);
            bookingRequest.setMovieSchedule(movieSchedule);
            bookingRequest.setSeat(seat);
            bookingRequest.setBookingStatus(BookingRequest.PaymentStatus.PAYMENT_PENDING);
            bookingRequestRepo.save(bookingRequest);
        }
        return true;
    }

}
