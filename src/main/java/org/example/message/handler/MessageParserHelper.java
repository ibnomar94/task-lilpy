package org.example.message.handler;

import org.example.strategy.tagsStrategy.*;

import java.util.List;

public class MessageParserHelper {

    public static int getMessageLength(int currentIndex, String message) {
        return Integer.parseInt(message.substring(currentIndex, currentIndex + 2), 16) * 2;
    }

    public static List<TagsResolver> getAvailableResolvers(){
        return List.of(
                new AmountResolver(),
                new CurrencyResolver(),
                new KernelResolver(),
                new PanResolver()
        );
    }
}
