package org.example.message.handler;

import org.example.entity.MessageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleMessageParser {

    private final SingleMessageParser singleMessageParser = new SingleMessageParser();
    private final MessageValidator messageValidator = new MessageValidator();

    public Map<String, List<MessageEntity>> parse(String messages){

        messageValidator.validateTotalLength(messages);

        int currentIndex = 4;
        Map<String, List<MessageEntity>> messageEntityList = new HashMap<>();

        while(currentIndex < messages.length()){
            String message = messageValidator.validateAndResolveMessage(currentIndex, messages);

            MessageEntity entity = singleMessageParser.parse(message);
            if(!messageEntityList.containsKey(entity.getKernel())){
                messageEntityList.put(entity.getKernel(), new ArrayList<>());
            }

            messageEntityList.get(entity.getKernel()).add(entity);

            currentIndex += message.length() + 6;
        }

        for(String kernel : messageEntityList.keySet()){
            System.out.println(messageEntityList.get(kernel));
        }

        return messageEntityList;
    }
}
