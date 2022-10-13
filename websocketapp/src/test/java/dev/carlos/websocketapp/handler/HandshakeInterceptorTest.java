package dev.carlos.websocketapp.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandshakeInterceptorTest {

    @Test
    void getSuperHero() {

        HandshakeInterceptor handshakeInterceptor = new HandshakeInterceptor();
        String superHero = handshakeInterceptor.getSuperHero();
        System.out.println(superHero);

    }
}