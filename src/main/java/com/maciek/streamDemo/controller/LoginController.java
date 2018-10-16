package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.request.LoginRequest;
import com.maciek.streamDemo.response.DefaultResponse;
import com.maciek.streamDemo.response.LoginResponse;
import com.maciek.streamDemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.tryLogUser(loginRequest);
    }

    @PostMapping("/logout")
    public DefaultResponse logout() {
        return new DefaultResponse("Logged out");
    }
}
