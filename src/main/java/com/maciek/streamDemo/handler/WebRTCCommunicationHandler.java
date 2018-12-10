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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class WebRTCCommunicationHandler extends TextWebSocketHandler {
    private static final Gson GSON = new GsonBuilder().create();
    private static final String PRESENTER = "presenter";
    private static final String VIEWER = "viewer";
    private static final String HELLO_MESSAGE = "helloMessage";
    private static final String BEAT_MESSAGE = "beatMessage";
    private static final String OK = "OK";
    private static Long ID = 1L;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    WebRTCCommunicationHandler() {
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
                addSession(PRESENTER, session);
            } else if (helloMessageAsString.startsWith(VIEWER)) {
                System.out.println("Adding viewer");
                addSession(helloMessageAsString + ID, session);
                ID++;
            }
        }
        if (beatMessage != null) {
            session.sendMessage(new TextMessage(OK));
            return;
        }
        System.out.println(String.format("Incoming message: {%s}", payload));
        //presenter -> viewer
        String id = session.getId();
        getAllViewers().forEach(viewerSession -> {
            try {
                closeDanglingSession(viewerSession);
                if (viewerSession.isOpen() && !id.equals(viewerSession.getId())) {
                    viewerSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getAllPresenters().forEach(presenterSession -> {
            try {
                closeDanglingSession(presenterSession);
                if (presenterSession.isOpen() && !id.equals(presenterSession.getId())) {
                    presenterSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void closeDanglingSession(WebSocketSession webSocketSession) throws IOException {
        if (!webSocketSession.isOpen()) {
            webSocketSession.close();
        }
    }

    private void addSession(String sessionName, WebSocketSession session) {
        this.sessions.put(sessionName, session);
    }

    private List<WebSocketSession> getAllViewers() {
        Set<Map.Entry<String, WebSocketSession>> sessionEntries = sessions.entrySet();
        return sessionEntries
                .stream()
                .filter(entry -> entry.getKey().startsWith(VIEWER))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private List<WebSocketSession> getAllPresenters() {
        Set<Map.Entry<String, WebSocketSession>> sessionEntries = sessions.entrySet();
        return sessionEntries
                .stream()
                .filter(entry -> entry.getKey().startsWith(PRESENTER))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
