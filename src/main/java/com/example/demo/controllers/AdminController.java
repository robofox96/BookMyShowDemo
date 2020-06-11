package com.example.demo.controllers;

import com.example.demo.apimodels.NewMovieRequestModel;
import com.example.demo.apimodels.NewMovieSceduleRequestModel;
import com.example.demo.apimodels.NewScreenLayoutRequest;
import com.example.demo.apimodels.ScreenLayoutModel;
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

    @RequestMapping(value = "/addMovie", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewMovie(@RequestBody NewMovieRequestModel newMovieRequestModel){
        return new ResponseEntity("Status : "+ adminService.addNewMovie(newMovieRequestModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/addTheater", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNewTheater(){
        return null;
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
