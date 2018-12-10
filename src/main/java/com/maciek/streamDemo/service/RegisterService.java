package com.maciek.streamDemo.service;

import com.maciek.streamDemo.RegisterStatus;
import com.maciek.streamDemo.persistence.entity.User;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.RegisterRequest;
import com.maciek.streamDemo.response.RegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterService.class);
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        LOGGER.info("Register request: " + registerRequest.toString());
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String passwordRetype = registerRequest.getPasswordRetype();
        String indexNumber = registerRequest.getIndexNumber();
        if (password.equals(passwordRetype)) {
            User registeringUser = new User();
            registeringUser.setEmail(email);
            registeringUser.setPassword(password);
            registeringUser.setIndexNumber(indexNumber);
            userRepository.save(registeringUser);
            return new RegisterResponse(RegisterStatus.OK, new Date());
        } else {
            return new RegisterResponse(RegisterStatus.PASSWORDS_NOT_EQUAL, new Date());
        }
    }
}
