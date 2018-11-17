package com.maciek.streamDemo.service;

import com.maciek.streamDemo.RegisterStatus;
import com.maciek.streamDemo.persistence.entity.User;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.RegisterRequest;
import com.maciek.streamDemo.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public RegisterResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String passwordRetype = registerRequest.getPasswordRetype();
        if (password.equals(passwordRetype)) {
            User registeringUser = new User();
            registeringUser.setEmail(email);
            registeringUser.setPassword(password);
            userRepository.save(registeringUser);
            return new RegisterResponse(RegisterStatus.OK, new Date());
        } else {
            return new RegisterResponse(RegisterStatus.PASSWORDS_NOT_EQUAL, new Date());
        }
    }
}
