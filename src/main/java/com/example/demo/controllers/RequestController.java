package com.example.demo.controllers;

import com.example.demo.services.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("request")
@CrossOrigin
public class RequestController {

    @Autowired
    RequestService requestService;

    @RequestMapping(value = "/getScreenLayout", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity getScreenLayout(@RequestParam(value = "theater_id", required = false) Integer theaterId,
                                          @RequestParam(value = "screen_id") Integer screenId){
        return new ResponseEntity(requestService.getScreenLayout(screenId,theaterId), HttpStatus.OK);
    }

}
