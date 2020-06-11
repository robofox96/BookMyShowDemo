package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends CrudRepository<Movie, Integer> {

    List<Movie> findAll();

}
