package dev.carlos.websocketapp.handler;

import com.google.gson.Gson;
import dev.carlos.websocketapp.models.PADOLabsMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom extends AbstractWebSocketHandler {

    public final static List<WebSocketSession> sessionList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("Connection established...");
        sessionList.add(session);

        session.sendMessage(new TextMessage("Welcome to the chat room! " + "seu usuário é: " + session.getAttributes().get("user").toString()));

        //TODO CHECK THIS
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        Gson gson = new Gson();
        PADOLabsMessage padoLabsMessage = gson.fromJson(message.getPayload(), PADOLabsMessage.class);

        System.out.println(padoLabsMessage);

        if(padoLabsMessage.getUser().equalsIgnoreCase(""))
        {
            System.out.println("User is empty");
            for(WebSocketSession webSocketSession : sessionList)
            {
               webSocketSession.sendMessage(new TextMessage(padoLabsMessage.getMessage()));
            }
        }

        for(WebSocketSession webSocketSession : sessionList)
        {
            System.out.println("Sending message to: " + webSocketSession.getAttributes().get("user").toString());
            if(session.getAttributes().get("user").toString().equalsIgnoreCase(padoLabsMessage.getUser()))
            {
                webSocketSession.sendMessage(new TextMessage(padoLabsMessage.getMessage()));
            }
        }

        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        sessionList.forEach(
                webSocketSession -> {
                    System.out.println(webSocketSession.getAttributes().get("user").toString());
                }
        );
        super.afterConnectionClosed(session, status);
    }
}

