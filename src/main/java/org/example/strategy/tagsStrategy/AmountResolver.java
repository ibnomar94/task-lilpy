package org.example.strategy.tagsStrategy;

import org.example.entity.MessageEntity;

import static org.example.message.property.Constants.AMOUNT;

public class AmountResolver implements TagsResolver {
    @Override
    public boolean isValid(String tag) {
        return tag.equalsIgnoreCase(AMOUNT);
    }

    @Override
    public void resolve(String value, MessageEntity messageEntity) {
        messageEntity.setAmount(resolveAmount(value));
    }

    private Float resolveAmount(String amount) {
        return Float.parseFloat(amount) / 100;
    }
}
