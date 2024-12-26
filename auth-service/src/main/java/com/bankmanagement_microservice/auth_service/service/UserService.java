package com.bankmanagement_microservice.auth_service.service;

import com.bankmanagement_microservice.auth_service.exception.UserAlreadyExistException;
import com.bankmanagement_microservice.auth_service.model.User;
import com.bankmanagement_microservice.auth_service.repository.UserRepository;
import com.bankmanagement_microservice.auth_service.request.RequestForCreateUser;
import com.bankmanagement_microservice.auth_service.request.RequestForLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(RequestForCreateUser request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserAlreadyExistException("User already exist ");
        }
        User user=new User(request.getName(),request.getUsername(),
                passwordEncoder.encode(request.getPassword()),request.getEmail());
        return userRepository.save(user);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException("User could not found by username : "+username));
    }
}
