package com.example.demo.services;

import com.example.demo.apimodels.BookingRequestModel;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
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
    PreBookingRepo preBookingRepo;

    @Autowired
    BookingRequestRepo bookingRequestRepo;

    private static final Integer WAIT_TIME_FOR_UNLOCKING = 600000;

    public Boolean lockSeats(BookingRequestModel bookingRequestModel) throws InterruptedException {
        List<Integer> preBookingIds = new ArrayList<>();
        User user = userRepo.findById(bookingRequestModel.getUserId()).orElse(null);
        MovieSchedule movieSchedule = movieScheduleRepo.findById(bookingRequestModel.getMovieScheduleId()).orElse(null);
        if(Objects.isNull(movieSchedule) || Objects.isNull(user)){
            throw new RuntimeException("Invalid Request Parameters" + bookingRequestModel.getMovieScheduleId());
        }
        for(Integer seatId : bookingRequestModel.getSeatIdList()){
            Seat seat = seatRepo.findById(seatId).orElse(null);
            if(Objects.nonNull(seat)){
                PreBooking preBookingLock = new PreBooking();
                BookingRequest bookingRequest = bookingRequestRepo.findByMovieScheduleIdAndSeatId(movieSchedule.getId(),seat.getId()).orElse(null);
                try{
                    preBookingLock.setMovieSchedule(movieSchedule);
                    preBookingLock.setSeat(seat);
                    preBookingLock.setUser(user);
                    if(Objects.nonNull(bookingRequest)){
                        throw new Exception("Seat already Booked");
                    }
                    preBookingLock = preBookingRepo.save(preBookingLock);
                    preBookingIds.add(preBookingLock.getId());
                }catch(Exception e){
                    log.info("Error while locking seat with info : " + preBookingLock);
                    log.error("Error Message :- ", e);
                    unlockSeats(preBookingIds,0);   //Immediately unlock seats that were locked in current transaction.
                    return false;
                }
            }else{
                throw new RuntimeException("Invalid Seat Id --> "+seatId);
            }
        }
        unlockSeats(preBookingIds,WAIT_TIME_FOR_UNLOCKING);
        return true;
    }

    @Async
    private void unlockSeats(List<Integer> preBookIds, Integer waitTime) throws InterruptedException {
        log.info("Starting Timer for locked seats");
        Thread.sleep(waitTime);
        try{
            for(Integer lockId : preBookIds){
                preBookingRepo.deleteById(lockId);
            }
        }catch (Exception e){
            log.error("Error while unlocking seats", e);
        }
    }

    public Boolean bookSeats(BookingRequestModel bookingRequestModel){
        User user = userRepo.findById(bookingRequestModel.getUserId()).orElse(null);
        MovieSchedule movieSchedule = movieScheduleRepo.findById(bookingRequestModel.getMovieScheduleId()).orElse(null);
        List<Integer> seatsToUnlock = new ArrayList<>();
        if(Objects.isNull(user) || Objects.isNull(movieSchedule))
            throw new RuntimeException("Invalid user/moviechedule id");
        List<Seat> updatedSeatList = new ArrayList<>();
        for(Integer seatId : bookingRequestModel.getSeatIdList()){
            Seat seat = seatRepo.findById(seatId).orElse(null);
            if(Objects.isNull(seat)){
                throw new RuntimeException("Invalid seat id : " + seatId);
            }
            PreBooking preBooking = preBookingRepo.findBySeatIdAndMovieScheduleIdAndUserId(seat.getId(),movieSchedule.getId(),user.getId()).orElse(null);
            if(Objects.isNull(preBooking)){
                throw new RuntimeException("Error while booking seat ID : " + seatId);
            }
            seatsToUnlock.add(preBooking.getId());
            updatedSeatList.add(seat);
        }
        //Unlock Seats and move for final booking
        try {
            unlockSeats(seatsToUnlock,0);
        } catch (InterruptedException e) {
            log.error("Error while unlocking seats for final booking");
            log.error(e.getMessage());
            return false;
        }

        //All seats are available and locks are removed to move to final booking.
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
