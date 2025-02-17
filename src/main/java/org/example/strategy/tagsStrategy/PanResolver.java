package org.example.strategy.tagsStrategy;

import org.example.entity.MessageEntity;
import org.example.message.property.Kernel;

import static org.example.message.property.Constants.PAN;

public class PanResolver implements TagsResolver {
    @Override
    public boolean isValid(String tag) {
        return tag.equalsIgnoreCase(PAN);
    }

    @Override
    public void resolve(String value, MessageEntity messageEntity) {
        messageEntity.setCardNumber(resolveCardNumber(value, messageEntity.getKernel()));
    }

    private String resolveCardNumber(String value, String kernel) {
        return kernel.equals(Kernel.AMEX.getKernelName()) ?
                value.substring(0, 15) :
                value.substring(0, Integer.min(value.length(), 16));
    }
}
