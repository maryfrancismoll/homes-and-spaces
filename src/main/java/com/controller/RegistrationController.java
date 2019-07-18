package com.controller;

import com.domain.User;
import com.model.Message;
import com.model.UserModel;
import com.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

/**
 * @author Maryfrancis Remo Moll
 *
 * Controller that handles the registration function
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    /**
     * Method handles POST request for registration of users
     * RequestBody is expecting object of type UserModel
     *
     * @return ResponseEntity
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity addUser(@RequestBody UserModel userModel){
        try {
            User user = registrationService.registerUser(userModel);
            return new ResponseEntity(new Message("Account " + user.getUserName() + " created."), HttpStatus.CREATED);
        }catch(AccountNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity(new Message("There was a problem creating the user. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(AccountException e){
            e.printStackTrace();
            return new ResponseEntity(new Message("Account already exists."), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(new Message("Unable to register. Please try again. "), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
