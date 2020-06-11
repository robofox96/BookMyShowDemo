package com.example.demo.controllers;

import com.example.demo.apimodels.BookingRequestModel;
import com.example.demo.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
@CrossOrigin
@Slf4j
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/lockSeats",produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity lockSeats(@RequestParam(value = "seat_id_list") List<Integer> seatIdList) throws InterruptedException {
        return new ResponseEntity("Status : " + bookingService.lockSeats(seatIdList), HttpStatus.OK);
    }

    @RequestMapping(value = "/bookSeats",produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity bookSeats(@RequestBody BookingRequestModel bookingRequestModel){
        return null;
    }

}
