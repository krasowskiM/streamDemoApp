package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.request.RegisterRequest;
import com.maciek.streamDemo.response.RegisterResponse;
import com.maciek.streamDemo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PutMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }
}
