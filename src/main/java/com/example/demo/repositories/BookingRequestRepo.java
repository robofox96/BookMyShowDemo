package com.example.demo.repositories;

import com.example.demo.entities.BookingRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRequestRepo extends CrudRepository<BookingRequest,Integer> {

    Optional<BookingRequest> findByMovieScheduleIdAndSeatId(Integer movieScheduleId, Integer seatId);

}
