package com.jean.mobileappws.service;

import com.jean.mobileappws.model.response.UserRest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserRest> createUser(UserRest userRest);
}
