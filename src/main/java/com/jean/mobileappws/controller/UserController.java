package com.jean.mobileappws.controller;

import com.jean.mobileappws.model.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
    ) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable Integer userId) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail("jean.leal22@gmail.com");
        returnValue.setFirstName("Jean");
        returnValue.setLastName("Leal");
        returnValue.setUserId(userId);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRest userRest) {
        UserRest returnValue = new UserRest();
        returnValue.setFirstName(userRest.getFirstName());
        returnValue.setLastName(userRest.getLastName());
        returnValue.setEmail(userRest.getEmail());
        returnValue.setPassword(userRest.getPassword());
        returnValue.setUserId(1);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }
}
