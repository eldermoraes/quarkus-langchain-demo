package com.eldermoraes.tools;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = EmailService.class)
public interface AssistantWithContext {

    /**
     * Ask the LLM to create a poem about the given topic.
     *
     * @param topic the topic of the poem
     * @param lines the number of line of the poem
     * @return the poem
     */
    @SystemMessage("You are a professional poet. Translate all your poems to Brazilian Portuguese.")
    @UserMessage("Write a poem about {topic}. The poem should be {lines} lines long. Then send this poem by email.")
    String writeAPoem(String topic, int lines);

}