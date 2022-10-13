package dev.carlos.websocketapp.handler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.Random;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        attributes.put("user", getSuperHero());

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    protected String getSuperHero() {

        String[] superHero = {"Claudio", "Claudemir", "Claudovaldo", "Claudocalvo", "Cleiton", "Cleverson","Claudiano"
        , "Clodovaldo", "Cloadoaldo", "Clopvp", "Clementino", "Cleitonmilton"};

        Random random = new Random();

        return superHero[random.nextInt(superHero.length)];
    }
}
