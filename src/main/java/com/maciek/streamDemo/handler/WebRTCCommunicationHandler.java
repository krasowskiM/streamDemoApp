package com.maciek.streamDemo.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebRTCCommunicationHandler extends TextWebSocketHandler {
    private static final Gson GSON = new GsonBuilder().create();
    private static final String PRESENTER = "presenter";
    private static final String VIEWER = "viewer";
    private static final String HELLO_MESSAGE = "helloMessage";
    private static final String BEAT_MESSAGE = "beatMessage";
    private static final String OK = "OK";
    private static Long ID = 1L;
    private final RTCCommunicationService rtcCommunicationService;

    @Autowired
    WebRTCCommunicationHandler(RTCCommunicationService rtcCommunicationService) {
        this.rtcCommunicationService = rtcCommunicationService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        JsonObject jsonObject = GSON.fromJson(message.getPayload(), JsonObject.class);
        String payload = jsonObject.toString();
        JsonElement helloMessage = jsonObject.get(HELLO_MESSAGE);
        JsonElement beatMessage = jsonObject.get(BEAT_MESSAGE);
        if (helloMessage != null) {
            String helloMessageAsString = helloMessage.getAsString();
            if (helloMessageAsString.equals(PRESENTER)) {
                System.out.println("Adding presenter");
                rtcCommunicationService.addSession(PRESENTER, session);
            } else if (helloMessageAsString.startsWith(VIEWER)) {
                System.out.println("Adding viewer");
                rtcCommunicationService.addSession(helloMessageAsString + ID, session);
                ID++;
            }
        }
        if (beatMessage != null) {
            session.sendMessage(new TextMessage(OK));
            return;
        }
        System.out.println(String.format("Incoming message: {%s}", payload));
        rtcCommunicationService.propagateMessages(session, message);
    }

}
