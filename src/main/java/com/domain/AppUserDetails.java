package com.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Maryfrancis Remo Moll
 *
 * Implements UserDetails, which is used by springframework in authentication
 */
public class AppUserDetails extends User implements UserDetails {

    public AppUserDetails(User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        this.getRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        });

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
