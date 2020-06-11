package com.example.demo.repositories;

import com.example.demo.entities.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepo extends CrudRepository<Seat, Integer> {

    Optional<Seat> findById(Integer id);

    List<Seat> findAllByScreenIdAndTierId(Integer screen_id, Integer tier_id);

}
