package com.redhat.developers.embedding;

import com.redhat.developers.embedding.BookingTools;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = BookingTools.class, retrieverSupplier = RegisterAiService.BeanRetrieverSupplier.class)
public interface AssistantForCustomerSupport {

    @SystemMessage({
            "You are a customer support agent of a car rental company named 'Miles of Smiles'.",
            "Before providing information about booking or cancelling booking, you MUST always check:",
            "booking number, customer name and surname.",
            "Today is {current_date}."
    })
    String chat(@MemoryId Object id, @UserMessage String userMessage);
}