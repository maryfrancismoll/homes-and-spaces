package com.repository;

import com.domain.UserInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInformationRepository extends CrudRepository<UserInformation, Long>{

    UserInformation findByUserId(Long userId);
    //public UserInformation findByUserNameIgnoreCase(String userName);
}
