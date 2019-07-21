package com.controller;

import com.domain.Space;
import com.model.BookingModel;
import com.model.Message;
import com.service.BookingService;
import com.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    @ResponseBody
    public List<Space> getAllAvailableSpaces(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) Integer categoryId){

        return bookingService.getAvailableSpaces(from, to, categoryId);
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public ResponseEntity<?> bookSpace(@RequestBody BookingModel bookingModel){
        boolean isSuccessful = bookingService.bookSpace(bookingModel);
        if(isSuccessful){
            return new ResponseEntity(new Message("Successfully booked the space."), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Message("Failed to book the space."), HttpStatus.BAD_REQUEST);
    }
}
