package com.maciek.streamDemo.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketDataHandler extends TextWebSocketHandler {
    private static final Gson GSON = new GsonBuilder().create();

    @Autowired
    SocketDataHandler() {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonPrimitive jsonObject = GSON.fromJson(message.getPayload(), JsonPrimitive.class);
        System.out.println(String.format("Incoming message: {%s}", jsonObject.getAsString()));
        TextMessage response = new TextMessage("close");
        PongMessage pongMessage = new PongMessage();
        session.sendMessage(pongMessage);
    }
}
