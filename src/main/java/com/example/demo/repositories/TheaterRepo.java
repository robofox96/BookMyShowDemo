package com.example.demo.repositories;

import com.example.demo.entities.Theater;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepo extends CrudRepository<Theater, Integer> {
}
