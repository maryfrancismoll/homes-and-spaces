package com.repository;

import com.domain.UserRole;
import com.domain.UserRoleKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Maryfrancis Remo Moll
 *
 * Repository that performs the updates on the UserRole entity
 */
public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleKey> {

    List<UserRole> findByUserId(Long userId);
}
