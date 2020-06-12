package com.example.demo.repositories;

import com.example.demo.entities.PreBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreBookingRepo extends CrudRepository<PreBooking, Integer> {

    void deleteById(Integer id);

    Optional<PreBooking> findBySeatIdAndMovieScheduleIdAndUserId(Integer seatId, Integer movieScheduleId, Integer userId);

}
