package com.redhat.developers.memory;

import static dev.langchain4j.data.message.UserMessage.userMessage;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.output.Response;

@Path("/")
public class DeveloperResource {

    @Inject
    AssistantWithMemory assistant;

    @GET
    @Path("/memory")
    @Produces(MediaType.TEXT_PLAIN)
    public String memory() {
        String msg1 = "How do I write a REST endpoint in Java using Quarkus?";

        String response = "[User]: " + msg1 + "\n\n" +
                "[LLM]: "+ assistant.chat(1, msg1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2 = "Create a test of the first step. " +
                "Be short, 15 lines of code maximum.";

        response += "[User]: " + msg2 + "\n\n"+
                "[LLM]: "+ assistant.chat(1, msg2);

        return response;

    }

    @GET
    @Path("/guess")
    @Produces(MediaType.TEXT_PLAIN)
    public String guess() {
        String msg1FromUser1 = "Hello, my name is Klaus and I'm a doctor";

        String response = "[User1]: " + msg1FromUser1 + "\n\n" +
                "[LLM]: " + assistant.chat(1, msg1FromUser1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg1FromUser2 = "Hi, I'm Francine and I'm a lawyer";

        response += "[User2]: " + msg1FromUser2 + "\n\n" +
                "[LLM]: " + assistant.chat(2, msg1FromUser2) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2FromUser2 = "What is my name?";

        response += "[User2]: " + msg2FromUser2 + "\n\n" +
                "[LLM]: " + assistant.chat(2, msg2FromUser2) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2FromUser1 = "What is my profession?";

        response += "[User1]: " + msg2FromUser1 + "\n\n" +
                "[LLM]: " + assistant.chat(1, msg2FromUser1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        return response;
    }
}