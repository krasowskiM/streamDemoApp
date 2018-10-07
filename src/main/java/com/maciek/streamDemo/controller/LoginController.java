package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.LoginStatus;
import com.maciek.streamDemo.response.DefaultResponse;
import com.maciek.streamDemo.response.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/login")
    public LoginResponse login(){
        return new LoginResponse(LoginStatus.OK, new Date());
    }

    @PostMapping("/logout")
    public DefaultResponse logout(){
        return new DefaultResponse("Logged out");
    }
}
