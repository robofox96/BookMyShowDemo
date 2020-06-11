package com.example.demo.services;

import com.example.demo.apimodels.NewMovieRequestModel;
import com.example.demo.apimodels.NewMovieSceduleRequestModel;
import com.example.demo.apimodels.NewScreenLayoutRequest;
import com.example.demo.apimodels.ScreenLayoutModel;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AdminService {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    TheaterRepo theaterRepo;

    @Autowired
    ScreenRepo screenRepo;

    @Autowired
    TierRepo tierRepo;

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    MovieScheduleRepo movieScheduleRepo;

    public Boolean addNewMovie(NewMovieRequestModel newMovieRequestModel){
        try{
            Movie newMovie = new Movie();
            newMovie.setName(newMovieRequestModel.getName());
            newMovie.setDuration(newMovieRequestModel.getDuration());
            newMovie.setGenre(newMovieRequestModel.getGenre());
            newMovie.setLanguage(newMovieRequestModel.getLanguage());
            movieRepo.save(newMovie);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean addNewMovieSchedule(NewMovieSceduleRequestModel newMovieSceduleRequestModel){
        try{
            MovieSchedule movieSchedule = new MovieSchedule();
            Movie movie = movieRepo.findById(newMovieSceduleRequestModel.getMovieId()).orElse(null);
            Screen screen = screenRepo.findById(newMovieSceduleRequestModel.getScreen_id()).orElse(null);
            Theater theater = theaterRepo.findById(newMovieSceduleRequestModel.getScreen_id()).orElse(null);
            if(Objects.isNull(movie) || Objects.isNull(screen) || Objects.isNull(theater))
                throw new Exception("Given id is wrong");
            movieSchedule.setMovie(movie);
            movieSchedule.setTheater(theater);
            movieSchedule.setScreen(screen);
            movieSchedule.setStartTime(newMovieSceduleRequestModel.getStartTime());
            movieSchedule.setEndTime(newMovieSceduleRequestModel.getEndTime());
            movieScheduleRepo.save(movieSchedule);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean updateScreenLayout(ScreenLayoutModel screenLayoutModel){
        Screen screen = screenRepo.findById(screenLayoutModel.getScreen_id()).orElse(null);
        if(Objects.isNull(screen))
            throw new RuntimeException("Invalid Screen Id");
        try{
            for(ScreenLayoutModel.TierLayout tierLayout : screenLayoutModel.getScreenLayout()){
                Tier tierRequest = tierLayout.getTier();
                Tier existingTier = tierRepo.findById(tierRequest.getId()).orElse(null);
                if(Objects.isNull(existingTier)){
                    existingTier = new Tier();
                }
                existingTier.setScreen(screen);
                existingTier.setName(tierRequest.getName());
                existingTier.setPrice(tierRequest.getPrice());
                tierRepo.save(existingTier);
                for(Seat seat : tierLayout.getSeatList()){
                    Seat existingSeat = seatRepo.findById(seat.getId()).orElse(null);
                    if(Objects.isNull(existingSeat)){
                        existingSeat = new Seat();
                    }
                    existingSeat.setStatus(seat.getStatus());
                    existingSeat.setScreen(screen);
                    existingSeat.setTier(existingTier);
                    seatRepo.save(existingSeat);
                }
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean addNewScreenLayout(NewScreenLayoutRequest newScreenLayoutRequest){
        try{
            Screen screen = screenRepo.findById(newScreenLayoutRequest.getScreenId()).orElse(null);
            if(Objects.nonNull(screen)){
                for(NewScreenLayoutRequest.TierDetails tierDetails : newScreenLayoutRequest.getTierDetailsList()){
                    Tier newTier = new Tier();
                    newTier.setName(tierDetails.getTierName());
                    newTier.setPrice(tierDetails.getTierPrice());
                    newTier.setScreen(screen);
                    tierRepo.save(newTier);
                    for(int i=0;i<tierDetails.getNoOfSeats();i++){
                        Seat newSeat = new Seat();
                        newSeat.setTier(newTier);
                        newSeat.setScreen(screen);
                        newSeat.setStatus(Seat.Status.EMPTY);
                        seatRepo.save(newSeat);
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

}
