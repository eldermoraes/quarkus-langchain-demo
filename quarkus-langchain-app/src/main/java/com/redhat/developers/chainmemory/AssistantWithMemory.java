package com.redhat.developers.chainmemory;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
interface AssistantWithMemory {

    @SystemMessage({
            "You have to answer directly only the question you were asked. Don't assume or explain any other thing.",
    })
    String chat(@MemoryId Integer id, @UserMessage String msg);

}