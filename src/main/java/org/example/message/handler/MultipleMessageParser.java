package org.example.message.handler;

import org.example.entity.MessageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.message.handler.MessageParserHelper.getMessageLength;
import static org.example.message.property.Constants.*;

public class MultipleMessageParser {

    private final SingleMessageParser singleMessageParser = new SingleMessageParser();
    private final MessageValidator messageValidator = new MessageValidator();

    public Map<String, List<MessageEntity>> parse(String messages) {

        messageValidator.validateTotalLength(messages);

        int currentIndex = 4;
        Map<String, List<MessageEntity>> messageEntityList = new HashMap<>();

        while (currentIndex < messages.length()) {
            messageValidator.validateAndResolveMessage(currentIndex, messages);
            String message = getCurrentMessage(currentIndex, messages);
            MessageEntity entity = singleMessageParser.parse(message);

            if (!messageEntityList.containsKey(entity.getKernel())) {
                messageEntityList.put(entity.getKernel(), new ArrayList<>());
            }

            messageEntityList.get(entity.getKernel()).add(entity);

            currentIndex += message.length() + START_TAG_LENGTH + END_TAG_LENGTH + MESSAGE_LENGTH;
        }

        for (String kernel : messageEntityList.keySet()) {
            System.out.println(messageEntityList.get(kernel));
        }

        return messageEntityList;
    }

    public String getCurrentMessage(int currentIndex, String message) {
        currentIndex += 2;
        int length = getMessageLength(currentIndex, message);
        currentIndex += 2 + length;
        return message.substring(currentIndex - length, currentIndex);
    }
}
