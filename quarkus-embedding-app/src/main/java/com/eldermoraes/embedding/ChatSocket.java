package com.eldermoraes.embedding;

import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import jakarta.websocket.OnOpen;

@WebSocket(path = "/chat")
public class ChatSocket {

    private final AssistantForCustomerSupport assistant;

    public ChatSocket(AssistantForCustomerSupport assistant) {
        this.assistant = assistant;
    }

    @OnOpen
    public String onOpen() {
        return "Hello from Miles of Smiles, how can we help you?";
    }

    @OnTextMessage
    public String onMessage(String userMessage){
        return assistant.chat(userMessage);
    }
}