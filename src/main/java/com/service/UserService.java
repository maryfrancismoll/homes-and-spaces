package com.service;

import com.domain.User;
import com.domain.UserInformation;
import com.model.UserModel;
import com.repository.UserInformationRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInformationRepository userInformationRepository;

    public UserModel getUserDetails() throws Exception{
        Long currentUserId = CommonService.getCurrentUserId();

        UserInformation userInformation = userInformationRepository.findOne(currentUserId);
        if (userInformation == null || userInformation.getUserId() == null){
            throw new AccountNotFoundException("User not found.");
        }
        User user = userRepository.findOne(userInformation.getUserId());
        return new UserModel(user, userInformation, false);
    }
}
