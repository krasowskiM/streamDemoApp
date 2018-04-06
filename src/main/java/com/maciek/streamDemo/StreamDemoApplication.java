package com.maciek.streamDemo;

import com.maciek.streamDemo.handler.SocketDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableWebSocket
public class StreamDemoApplication implements WebSocketConfigurer {
	private final SocketDataHandler socketDataHandler;

	@Autowired
	public StreamDemoApplication(SocketDataHandler socketDataHandler) {
		this.socketDataHandler = socketDataHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(StreamDemoApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketDataHandler, "/enableSocketConnection").setAllowedOrigins("*");
	}
}
