package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.response.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/getResponse")
    public DefaultResponse getDefaultResponse(){
        return new DefaultResponse("Welcome to the app!");
    }
}
