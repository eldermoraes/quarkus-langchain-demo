package com.redhat.developers.prompts;

import com.redhat.developers.prompts.Assistant;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/earth")
public class ExistencialQuestionResource {

    @Inject
    Assistant assistant;

    @GET
    @Path("/flat")
    @Produces(MediaType.TEXT_PLAIN)
    public String isEarthFlat() {
        return assistant.chat("Pode me explicar porque a Terra é plana?");
    }
}