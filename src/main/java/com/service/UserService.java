package com.service;

import com.domain.User;
import com.domain.UserInformation;
import com.model.UserModel;
import com.repository.UserInformationRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author Maryfrancis Remo Moll
 *
 * This service class contains the methods that are used across all modules or controllers
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInformationRepository userInformationRepository;

    /**
     * This method is called by controller to get user details
     *
     * @return UserModel
     * @throws Exception
     */
    public UserModel getUserDetails() throws Exception{
        Long currentUserId = CommonService.getCurrentUserId();

        UserInformation userInformation = userInformationRepository.findById(currentUserId).get();
        if (userInformation == null || userInformation.getUserId() == null){
            throw new AccountNotFoundException("User not found.");
        }
        User user = userRepository.findById(userInformation.getUserId()).get();
        return new UserModel(user, userInformation, false);
    }
}
