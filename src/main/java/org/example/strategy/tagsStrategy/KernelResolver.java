package org.example.strategy.tagsStrategy;

import org.example.entity.MessageEntity;
import org.example.message.property.Kernel;

import java.util.Optional;

import static org.example.message.property.Constants.KERNEL;
import static org.example.message.property.Constants.UNKNOWN_KERNEL;

public class KernelResolver implements TagsResolver {
    @Override
    public boolean isValid(String tag) {
        return tag.equalsIgnoreCase(KERNEL);
    }

    @Override
    public void resolve(String value, MessageEntity messageEntity) {
        messageEntity.setKernel(resolveKernel(value));
    }

    private String resolveKernel(String value) {
        String kernelValue = value.substring(0, 2);
        Optional<Kernel> kernel = Kernel.getByValue(kernelValue);
        return kernel.isPresent() ? kernel.get().getKernelName() : UNKNOWN_KERNEL;
    }
}
