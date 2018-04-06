package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.response.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/getResponse")
    public DefaultResponse main() {
        DefaultResponse defaultResponse = new DefaultResponse();
        System.out.println(defaultResponse.getId());
        return defaultResponse;
    }

//    @PostMapping("/initializeStream")
//    public DefaultResponse getStream(){
//
//    }
}
