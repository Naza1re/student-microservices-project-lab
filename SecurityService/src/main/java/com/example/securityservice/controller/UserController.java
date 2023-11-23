package com.example.securityservice.controller;

import com.example.securityservice.exception.UserNotFoundException;
import com.example.securityservice.model.UserCredential;
import com.example.securityservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userCredential) {
        this.userService = userCredential;
    }


    @GetMapping("/all-users")
    public ResponseEntity<List<UserCredential>> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserCredential> getUserById(@PathVariable Long  id )throws UserNotFoundException {
        return userService.getUserByID(id);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<UserCredential> updateUserById(@RequestBody UserCredential userCredential, @PathVariable Long id) throws UserNotFoundException {
        return userService.updateUserById(id,userCredential);
    }
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteUserById(@PathVariable Long id) throws UserPrincipalNotFoundException {
        return userService.deleteUserById(id);
    }
}
