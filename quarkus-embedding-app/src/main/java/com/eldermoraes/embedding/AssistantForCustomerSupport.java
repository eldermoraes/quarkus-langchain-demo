package com.eldermoraes.embedding;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;

@RegisterAiService(tools = BookingTools.class)
@SessionScoped
public interface AssistantForCustomerSupport {

    @SystemMessage({
            "You are a customer support agent of a car rental company named 'Miles of Smiles'.",
            "Before providing information about booking or cancelling booking, you MUST always check:",
            "booking number, customer name and surname and the Cancellation policy in the Terms of Use",
            "Before cancelling, confirm with the customer that they want to proceed",
            "Do NOT cancel the booking if the start date is not compliant with the Cancellation policy in the Terms of Use",
            "Today is {current_date}."
    })
    String chat(@UserMessage String userMessage);
}