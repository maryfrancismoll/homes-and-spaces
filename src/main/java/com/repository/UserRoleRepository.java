package com.repository;

import com.domain.UserRole;
import com.domain.UserRoleKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleKey> {

    List<UserRole> findByUserId(Long userId);

    List<UserRole> findByRoleId(Long roleId);
}
