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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebRTCCommunicationHandler extends TextWebSocketHandler {
    private static final Gson GSON = new GsonBuilder().create();
    private static final String PRESENTER = "presenter";
    private static final String VIEWER = "viewer";
    private static final String HELLO_MESSAGE = "helloMessage";
    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    WebRTCCommunicationHandler() {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        JsonObject jsonObject = GSON.fromJson(message.getPayload(), JsonObject.class);
        String payload = jsonObject.toString();
        System.out.println(String.format("Incoming message: {%s}", payload));
        JsonElement helloMessage = jsonObject.get(HELLO_MESSAGE);
        if (helloMessage != null && helloMessage.getAsString().equals(PRESENTER)) {
            System.out.println("Adding presenter");
            addSession(PRESENTER, session);
        } else if (helloMessage != null && helloMessage.getAsString().equals(VIEWER)) {
            System.out.println("Adding viewer");
            addSession(VIEWER, session);
        }

        //presenter -> viewer
        String id = session.getId();
        WebSocketSession presenter = sessions.get(PRESENTER);
        WebSocketSession viewer = sessions.get(VIEWER);
        if(presenter != null && presenter.getId().equals(id)){
            System.out.println("Sending to viewer");
            viewer.sendMessage(message);
            //viewer -> presenter
        } else if(presenter != null && viewer != null && viewer.getId().equals(id)){
            System.out.println("Sending to presenter");
            presenter.sendMessage(message);
        }
        //no echo
    }

    private void addSession(String sessionName, WebSocketSession session) {
        this.sessions.put(sessionName, session);
    }
}
