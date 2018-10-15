package com.maciek.streamDemo.service;

import com.maciek.streamDemo.LoginStatus;
import com.maciek.streamDemo.persistence.entity.User;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.LoginRequest;
import com.maciek.streamDemo.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse tryLogUser(LoginRequest loginRequest) {
        LOGGER.info("Login request: " + loginRequest.toString());
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User userByEmail = userRepository.findByEmail(email);
        LoginResponse loginResponse;
        if (userByEmail == null) {
            loginResponse = new LoginResponse(LoginStatus.INVALID_EMAIL, new Date());
        } else {
            String passwordFromEntity = userByEmail.getPassword();
            if (passwordFromEntity.equals(password)) {
                loginResponse = new LoginResponse(LoginStatus.OK, new Date());
            } else {
                loginResponse = new LoginResponse(LoginStatus.INVALID_PASSWORD, new Date());
            }
        }
        return loginResponse;
    }
}
