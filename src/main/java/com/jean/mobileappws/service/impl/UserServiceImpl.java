package com.jean.mobileappws.service.impl;

import com.jean.mobileappws.model.response.UserRest;
import com.jean.mobileappws.service.UserService;
import com.jean.mobileappws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private Map<String, UserRest> userRestMap;
    private Utils utils;

    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public ResponseEntity<UserRest> createUser(UserRest userRest) {
        UserRest returnValue = new UserRest();
        returnValue.setFirstName(userRest.getFirstName());
        returnValue.setLastName(userRest.getLastName());
        returnValue.setEmail(userRest.getEmail());
        returnValue.setPassword(userRest.getPassword());
        returnValue.setUserId(utils.generateUserId());

        if (userRestMap == null) {
            userRestMap = new HashMap<>();
        }

        userRestMap.put(returnValue.getUserId(), returnValue);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }
}
