package com.example.demo.controllers;

import com.example.demo.apimodels.*;
import com.example.demo.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/addUser", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewUser(@RequestBody NewUserRequest newUserRequest){
        return new ResponseEntity("Status : "+ adminService.addNewUser(newUserRequest),HttpStatus.OK);
    }

    @RequestMapping(value = "/addMovie", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewMovie(@RequestBody NewMovieRequestModel newMovieRequestModel){
        return new ResponseEntity("Status : "+ adminService.addNewMovie(newMovieRequestModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/addTheater", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewTheater(@RequestBody NewTheaterRequest newTheaterRequest){
        return new ResponseEntity("Status : "+ adminService.addNewTheater(newTheaterRequest),HttpStatus.OK);
    }

    @RequestMapping(value = "/addScreen", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewScreen(@RequestBody NewScreenRequest newScreenRequest){
        return new ResponseEntity("Status : "+ adminService.addNewScreen(newScreenRequest),HttpStatus.OK);
    }

    @RequestMapping(value = "/addMovieShowForTheater", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addMovieSchedule(@RequestBody NewMovieSceduleRequestModel newMovieSceduleRequestModel){
        return new ResponseEntity("Status : " + adminService.addNewMovieSchedule(newMovieSceduleRequestModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/addNewScreenLayout", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewScreenLayout(@RequestBody NewScreenLayoutRequest newScreenLayoutRequest){
        return new ResponseEntity("Status : " + adminService.addNewScreenLayout(newScreenLayoutRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateScreenLayout", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateScreenLayout(@RequestBody ScreenLayoutModel screenLayoutModel){
        return new ResponseEntity("Status : " + adminService.updateScreenLayout(screenLayoutModel), HttpStatus.OK);
    }

}
