package com.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maryfrancis Remo Moll
 *
 * This service class contains the methods that are used across all modules or controllers
 */
@Service
@PreAuthorize("isAuthenticated()")
public class CommonService {

    /**
     *
     * @return Map<String, Object> token details
     */
    public static Map<String, Object> getTokenDetails(){
        Map<String, Object> decodedDetails = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        if ( details instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)details;
            decodedDetails = (Map<String, Object>)oAuth2AuthenticationDetails.getDecodedDetails();
        }

        return decodedDetails;
    }

    /**
     *
     * @return Long id of logged in user
     */
    public static Long getCurrentUserId(){
        Long userId = null;

        Map<String, Object> decodedDetails = getTokenDetails();
        if(decodedDetails != null && decodedDetails.containsKey("user_id")){
            userId = new Long(decodedDetails.get("user_id").toString());
        }

        return userId;
    }

}
