package com.maciek.streamDemo;

import com.maciek.streamDemo.handler.WebRTCCommunicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@SpringBootApplication
@EnableWebSocket
public class StreamDemoApplication implements WebSocketConfigurer {
    private final WebRTCCommunicationHandler rtcCommunicationHandler;

    @Autowired
    public StreamDemoApplication(WebRTCCommunicationHandler rtcCommunicationHandler) {
        this.rtcCommunicationHandler = rtcCommunicationHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(StreamDemoApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(rtcCommunicationHandler, "/webRTCHandler")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
