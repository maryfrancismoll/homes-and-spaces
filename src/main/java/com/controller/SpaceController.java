package com.controller;

import com.domain.Space;
import com.model.Message;
import com.service.CommonService;
import com.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    @GetMapping
    @ResponseBody
    public List<Space> getAllSpaces(){
        return null;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getSpaceById(@PathVariable Integer id){
        return null;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Message> postNewSpace(@RequestBody Space space){
        //set current user id to space
        space.setUserId(CommonService.getCurrentUserId());

        //set from value, if not set
        if(space.getAvailableFrom() == null){
            space.setAvailableFrom(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }

        boolean isSuccessful = spaceService.saveNewSpace(space);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully saved new space."), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new Message("Failed to save new post."), HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Message> updateSpace(@RequestBody Space space){
        return null;
    }
}
