package com.service;

import com.domain.User;
import com.domain.UserInformation;
import com.domain.UserRole;
import com.model.UserModel;
import com.model.UserRoleEnum;
import com.repository.RoleRepository;
import com.repository.UserInformationRepository;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInformationRepository userInformationRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserModel userModel) throws Exception{

        User user = userRepository.findByUserName(userModel.getUserName());

        if (user != null && user.getId() != null){
            throw new AccountException("User with that username already exists.");
        }

        //save user first
        user = new User();
        user.setUserName(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        try{
            user = userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
            throw new AccountNotFoundException("User was not registered. ");
        }

        if (user != null && user.getId() == null){
            throw new AccountNotFoundException("User was not registered. ");
        }

        UserInformation userInformation = new UserInformation();
        userInformation.setUserId(user.getId());
        userInformation.setFirstName(userModel.getFirstName());
        userInformation.setLastName(userModel.getLastName());
        userInformation.setEmailAddress(userModel.getEmailAddress());
        try{
            userInformation = userInformationRepository.save(userInformation);
        }catch(Exception e){
            userRepository.delete(user);
            throw new AccountNotFoundException("User was not registered. ");
        }

        if (userInformation == null || userInformation.getUserId() == null){
            userRepository.delete(user);
            throw new AccountNotFoundException("User was not registered. ");
        }

        //save user role: default USER
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(UserRoleEnum.USER.getId());
            userRoleRepository.save(userRole);
        }catch(Exception e){
            userInformationRepository.delete(userInformation);
            userRepository.delete(user);
            throw new AccountNotFoundException("User was not registered. ");
        }

        return user;
    }

}
