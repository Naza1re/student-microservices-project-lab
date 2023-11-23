package com.example.securityservice.service;

import com.example.securityservice.exception.UserNotFoundException;
import com.example.securityservice.model.UserCredential;
import com.example.securityservice.repository.UserCredentialRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserCredentialRepository userCredentialRepository;

    public UserService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }


    public ResponseEntity<List<UserCredential>> getAllUsers(){

        List<UserCredential> userCredentials = userCredentialRepository.findAll();
        return
                new ResponseEntity<>(userCredentials, HttpStatus.OK);
    }

    public ResponseEntity<UserCredential> getUserByID(Long id) throws UserNotFoundException {
        Optional<UserCredential>opt_user = userCredentialRepository.findById(id);
        if (opt_user.isPresent()){
            return
                    new ResponseEntity<>(opt_user.get(),HttpStatus.OK);
        }
        else
            throw new UserNotFoundException("user with id '"+id+"' not found");
    }


    public ResponseEntity<UserCredential> updateUserById(Long id,UserCredential userCredential) throws UserNotFoundException {
        Optional<UserCredential> opt_user = userCredentialRepository.findById(id);

        if (opt_user.isPresent()){
            opt_user.get().setName(userCredential.getName());
            opt_user.get().setEmail(userCredential.getEmail());
            userCredentialRepository.save(opt_user.get());
            return
                    new ResponseEntity<>(opt_user.get(),HttpStatus.OK);
        }
        else
            throw new UserNotFoundException("user with id '"+ id+"' not found");

    }

    public HttpStatus deleteUserById(Long id) throws UserPrincipalNotFoundException {
        Optional<UserCredential> opt_user = userCredentialRepository.findById(id);
        if(opt_user.isPresent()){
            userCredentialRepository.delete(opt_user.get());
            return HttpStatus.OK;
        }
        else
            throw new UserPrincipalNotFoundException("user with id '"+id+"' not found");
    }
}
