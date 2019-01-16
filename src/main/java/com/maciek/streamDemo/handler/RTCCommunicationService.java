package com.maciek.streamDemo.handler;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
class RTCCommunicationService {
    private static final String PRESENTER = "presenter";
    private static final String VIEWER = "viewer";
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    void propagateMessages(WebSocketSession session, JsonObject messageObject) {
        //presenter -> viewer
        String fromId = session.getId();
        messageObject.addProperty("sessionId", fromId);
        TextMessage message = new TextMessage(messageObject.toString());
        getAllViewers().forEach(viewerSession -> {
            try {
                closeDanglingSession(viewerSession);
                if (viewerSession.isOpen() && !fromId.equals(viewerSession.getId())) {
                    viewerSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getAllPresenters().forEach(presenterSession -> {
            try {
                closeDanglingSession(presenterSession);
                if (presenterSession.isOpen() && !fromId.equals(presenterSession.getId())) {
                    presenterSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void addSession(String sessionName, WebSocketSession session) {
        sessions.put(sessionName, session);
    }

    private void closeDanglingSession(WebSocketSession webSocketSession) throws IOException {
        if (!webSocketSession.isOpen()) {
            webSocketSession.close();
        }
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
