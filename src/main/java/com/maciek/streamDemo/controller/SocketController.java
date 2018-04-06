package com.maciek.streamDemo.controller;

import com.maciek.streamDemo.response.SocketIOResponse;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Map;

@RestController
public class SocketController {
    private static final String SOCKET_IO = "C:\\projects\\streamDemo\\src\\main\\resources\\static\\js\\socket.io.js";
    private static final File SOCKET_IO_FILE = new File(SOCKET_IO);
    private static final String CONFIG = ":15000:10000:websocket";
    private final HttpServletResponse httpServletResponse;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public SocketController(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        this.httpServletResponse = httpServletResponse;
        this.httpServletRequest = httpServletRequest;
    }


    @RequestMapping(value = "/socket.io", method = {RequestMethod.GET, RequestMethod.POST})
    public String getSocketIoFile(@Nullable @RequestBody String raw) throws IOException, ServletException {
        System.out.println(raw);
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getRequestURL());
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (String key : parameterMap.keySet()) {
            System.out.println(key);
            System.out.println(Arrays.toString(parameterMap.get(key)));
        }
        System.out.println(httpServletRequest.getHeader("content-type"));
        String[] token = parameterMap.get("t");
        String string = token[0];
        System.out.println(string);
        return httpServletRequest.getSession().getId() + CONFIG;
    }
}
