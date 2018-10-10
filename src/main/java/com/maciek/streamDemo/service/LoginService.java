package com.maciek.streamDemo.service;

import com.maciek.streamDemo.LoginStatus;
import com.maciek.streamDemo.persistence.entity.User;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.LoginRequest;
import com.maciek.streamDemo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse tryLogUser(LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        User userByLogin = userRepository.findByLogin(login);
        LoginResponse loginResponse;
        if (userByLogin == null) {
            loginResponse = new LoginResponse(LoginStatus.INVALID_USERNAME, new Date());
        } else {
            String passwordFromEntity = userByLogin.getPassword();
            if (passwordFromEntity.equals(password)) {
                loginResponse = new LoginResponse(LoginStatus.OK, new Date());
            } else {
                loginResponse = new LoginResponse(LoginStatus.INVALID_PASSWORD, new Date());
            }
        }
        return loginResponse;
    }
}
