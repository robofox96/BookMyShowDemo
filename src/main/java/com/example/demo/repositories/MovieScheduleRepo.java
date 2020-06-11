package com.example.demo.repositories;

import com.example.demo.entities.MovieSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieScheduleRepo extends CrudRepository<MovieSchedule, Integer> {

    Optional<MovieSchedule> findById(Integer id);

    List<MovieSchedule> findAllByMovieId(Integer movieId);

}
