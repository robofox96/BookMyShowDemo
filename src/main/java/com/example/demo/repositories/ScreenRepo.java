package com.example.demo.repositories;

import com.example.demo.entities.Screen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepo extends CrudRepository<Screen, Integer> {
}
