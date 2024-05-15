package com.redhat.developers.embedding;

import java.io.IOException;
import java.io.UncheckedIOException;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.jboss.logging.Logger;

import io.quarkiverse.langchain4j.ChatMemoryRemover;

@ServerEndpoint(value = "/chat")
public class ChatSocket {

    private static final Logger LOG = Logger.getLogger(ChatSocket.class);

    private final AssistantForCustomerSupport assistant;
    private final ManagedExecutor managedExecutor;

    public ChatSocket(AssistantForCustomerSupport assistant, ManagedExecutor managedExecutor) {
        this.assistant = assistant;
        this.managedExecutor = managedExecutor;
    }

    @OnMessage
    public void onMessage(Session session, String userMessage) throws Exception {
        if (userMessage.equalsIgnoreCase("_ready_")) {
            return;
        }

        // we need to use a worker thread because OnMessage always runs on the event loop
        managedExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    session.getBasicRemote().sendText("[User]: " + userMessage);
                    session.getBasicRemote().sendText("[Assistant]: " + assistant.chat(session, userMessage));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                } catch (Exception e) {
                    LOG.error(e);
                }
            }
        });
    }

    @OnClose
    void onClose(Session session) {
        ChatMemoryRemover.remove(assistant, session);
    }

}