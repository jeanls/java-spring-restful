package com.jean.mobileappws.controller;

import com.jean.mobileappws.exceptions.UserServiceException;
import com.jean.mobileappws.model.response.UserRest;
import com.jean.mobileappws.model.request.UpdateUserRequest;
import com.jean.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> userRestMap;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
    ) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (true) throw new NullPointerException();
        if (userRestMap.containsKey(userId)) {
            return new ResponseEntity<>(userRestMap.get(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRest userRest) {
        return userService.createUser(userRest);
    }

    @PutMapping(
            path = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        if (Objects.isNull(userRestMap)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!userRestMap.containsKey(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserRest returnValue = userRestMap.get(userId);
        returnValue.setFirstName(updateUserRequest.getFirstName());
        returnValue.setLastName(updateUserRequest.getLastName());
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }
}
