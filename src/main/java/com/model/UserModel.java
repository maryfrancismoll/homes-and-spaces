package com.model;

import com.domain.User;
import com.domain.UserInformation;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Maryfrancis Remo Moll
 *
 * Model holds information used for user registration
 */
public class UserModel {

    private Long id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String emailAddress;

    @NotNull
    @NotEmpty
    private String password;

    public UserModel(){}

    public UserModel(User user, UserInformation userInformation, boolean showPassword){
        this.setId(user.getId());
        this.setFirstName(userInformation.getFirstName());
        this.setLastName(userInformation.getLastName());
        this.setEmailAddress(userInformation.getEmailAddress());
        this.setPassword(showPassword ? user.getPassword() : "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
