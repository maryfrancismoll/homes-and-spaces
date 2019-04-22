package com.repository;

import com.domain.UserInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Maryfrancis Remo Moll
 *
 * Repository that performs the updates on the UserInformation entity
 */
public interface UserInformationRepository extends CrudRepository<UserInformation, Long>{

    UserInformation findByUserId(Long userId);
}
