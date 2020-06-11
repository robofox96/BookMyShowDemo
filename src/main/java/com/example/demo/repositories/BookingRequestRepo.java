package com.example.demo.repositories;

import com.example.demo.entities.BookingRequest;
import org.springframework.data.repository.CrudRepository;

public interface BookingRequestRepo extends CrudRepository<BookingRequest,Integer> {


}
