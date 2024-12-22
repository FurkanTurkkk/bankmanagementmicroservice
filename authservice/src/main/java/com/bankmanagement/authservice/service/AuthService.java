package com.bankmanagement.authservice.service;

import com.bankmanagement.authservice.model.User;
import com.bankmanagement.authservice.repository.AuthRepository;
import com.bankmanagement.authservice.request.RequestForCreateUser;
import com.bankmanagement.authservice.utility.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthRepository authRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(RequestForCreateUser request){
        if(authRepository.findByUserName(request.getUserName()).isPresent()){
            throw new RuntimeException("Username already exist");
        }
        User user=new User(request.getUserName(),request.getPassword(),request.getRole());
        authRepository.save(user);
    }

    public String validateUser(RequestForCreateUser request){
        Optional<User> optionalUser=authRepository.findByUserName(request.getUserName());
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not found ");
        }
        User user=optionalUser.get();
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        return jwtUtil.generateToken(user.getUserName(),user.getRole());
    }
}
