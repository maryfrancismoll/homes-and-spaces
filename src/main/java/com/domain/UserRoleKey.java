package com.domain;

import java.io.Serializable;

/**
 * @author Maryfrancis Remo Moll
 *
 * The composite key of UserRole entity
 */
public class UserRoleKey implements Serializable {

    private Long userId;
    private Long roleId;

    public UserRoleKey(){}

    public UserRoleKey(Long userId, Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj instanceof UserRoleKey){
            UserRoleKey key = (UserRoleKey)obj;
            return this.getUserId().equals(key.getUserId()) &&
                    this.getRoleId().equals(key.getRoleId()) ;
        }else{
            return false;
        }

    }
}
