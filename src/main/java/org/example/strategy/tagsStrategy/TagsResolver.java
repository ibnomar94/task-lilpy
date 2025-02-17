package org.example.strategy.tagsStrategy;

import org.example.entity.MessageEntity;

public interface TagsResolver {

    boolean isValid(String tag);

    void resolve(String value, MessageEntity messageEntity);
}
