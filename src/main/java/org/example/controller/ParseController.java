package org.example.controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.MessageEntity;
import org.example.message.handler.SingleMessageParser;

@Path("/parse")
public class ParseController {
    private final SingleMessageParser singleMessageParser = new SingleMessageParser();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public MessageEntity parseSingleMessage(String message) {
        return singleMessageParser.parse(message);
    }
}
