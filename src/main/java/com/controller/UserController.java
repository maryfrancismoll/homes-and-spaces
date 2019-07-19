package com.controller;

import com.model.Message;
import com.model.UserModel;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.nio.file.AccessDeniedException;

/**
 * @author Maryfrancis Remo Moll
 *
 * Controller that handles functions related to users
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Method gets the details of the currently-logged-in user
     *
     * @return ResponseEntity<?>
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getUser(){
        try {
            UserModel user = userService.getUserDetails();
            return new ResponseEntity(user, HttpStatus.CREATED);
        }catch(AccessDeniedException e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(AccountNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity(new Message("The user details cannot be retrieved, possibly not existing."), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(new Message("Unable to execute properly. Please try again. "), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
