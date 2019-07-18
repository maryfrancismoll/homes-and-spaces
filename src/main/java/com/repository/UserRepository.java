package com.repository;

import com.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Maryfrancis Remo Moll
 *
 * Repository that performs the updates on the User entity
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String emailAddress);
}
