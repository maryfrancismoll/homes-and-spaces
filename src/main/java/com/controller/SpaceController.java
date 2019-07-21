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

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getSpaceById(@PathVariable Integer id){
        return null;
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public List<Space> getUserSpaces(){
        return spaceService.getAllUsersSpaces();
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
        //set from value, if not set
        if(space.getAvailableFrom() == null){
            space.setAvailableFrom(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }

        boolean isSuccessful = spaceService.updateSpace(space);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully updated space."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Message("Failed to update space."), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteSpace(@PathVariable Integer id){
        boolean isSuccessful = spaceService.deleteSpace(id);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully deleted space."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Message("Failed to delete space. Please check if you are the creator of this space post."), HttpStatus.BAD_REQUEST);
    }
}
