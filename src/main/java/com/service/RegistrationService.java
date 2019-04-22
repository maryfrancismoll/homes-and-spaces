package com.service;

import com.domain.User;
import com.domain.UserInformation;
import com.domain.UserRole;
import com.model.UserModel;
import com.model.UserRoleEnum;
import com.repository.UserInformationRepository;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

/**
 * @author Maryfrancis Remo Moll
 *
 * This service class contains the methods that are used across all modules or controllers
 */
@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInformationRepository userInformationRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This method performs the saving of the user details in the tables involved.
     *
     * @param userModel
     * @return User
     * @throws Exception
     */
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

            if (user != null && user.getId() == null){
                throw new Exception();
            }

            UserInformation userInformation = new UserInformation();
            userInformation.setUserId(user.getId());
            userInformation.setFirstName(userModel.getFirstName());
            userInformation.setLastName(userModel.getLastName());
            userInformation.setEmailAddress(userModel.getEmailAddress());

            userInformation = userInformationRepository.save(userInformation);
            if (userInformation == null || userInformation.getUserId() == null){
                // Delete user if saving user information failed
                userRepository.delete(user);
                throw new Exception();
            }

            //save user role: default USER
            try {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(UserRoleEnum.USER.getId());
                userRoleRepository.save(userRole);
            }catch(Exception e){
                // Delete user information and user if saving user role failed
                userInformationRepository.delete(userInformation);
                userRepository.delete(user);
                throw new Exception();
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new AccountNotFoundException("User was not registered. ");
        }
        return user;
    }

}
