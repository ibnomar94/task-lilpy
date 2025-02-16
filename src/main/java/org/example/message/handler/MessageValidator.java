package org.example.message.handler;

import java.security.InvalidParameterException;

import static org.example.message.handler.MessageParserHelper.getMessageLength;

public class MessageValidator {
    private static final String START = "02";
    private static final String END = "03";

    public void validateTotalLength(String message) {
        int totalLength = message.length(); //e.g. 0042

        if(totalLength <= 4) {
            throw new InvalidParameterException("Invalid message: length field should be of 2 bytes");
        }

        int lenOfMessage = Integer.parseInt(message.substring(0, 4), 16)*2;

        if(totalLength-4 != lenOfMessage){
            throw new InvalidParameterException("Invalid message: length field does not match the length of the message");
        }
    }

    public String validateAndResolveMessage(int currentIndex, String message) {
        String start = message.substring(currentIndex, currentIndex + 2);

        if(!start.equals(START)){
            throw new InvalidParameterException("Invalid message: start of the message not found");
        }
        currentIndex += 2;

        int length = getMessageLength(currentIndex,message);
        currentIndex += 2 + length;

        String end = message.substring(currentIndex, currentIndex+2);
        if(!end.equals(END)){
            throw new InvalidParameterException("Invalid message: end of the message not found");
        }

        return message.substring(currentIndex-length, currentIndex);
    }
}
