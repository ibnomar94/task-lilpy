package org.example.message.handler;

import org.example.entity.MessageEntity;
import org.example.message.property.Kernel;

import java.util.Currency;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.message.property.Constants.*;

public class MessageParserHelper {

    private static Map<Integer, Currency> currencies = null;

    static {
        currencies = Currency.getAvailableCurrencies().stream().collect(Collectors.toMap(Currency::getNumericCode, Function.identity(), (key1, key2) -> key1));
    }

    public static String resolveKernel(String value) {
        String kernelValue = value.substring(0, 2);
        Optional<Kernel> kernel = Kernel.getByValue(kernelValue);
        return kernel.isPresent()? kernel.get().getKernelName() : UNKNOWN_KERNEL;
    }

    public static String resolveCardNumber(String value, String kernel) {
        return kernel.equals(Kernel.AMEX.getKernelName())?
                value.substring(0, 15) :
                value.substring(0, Integer.min(value.length(), 16));
    }

    public static String resolveCurrency(String currency) {
        int cur = Integer.parseInt(currency);

        if (currencies.containsKey(cur)) {
            return currencies.get(cur).getCurrencyCode();
        }

        return null;
    }

    public static Float resolveAmount(String amount) {
        return Float.parseFloat(amount) / 100;
    }

    public static void addTag(String tag, String value, MessageEntity messageEntity) {
        switch (tag.toUpperCase()){
            case KERNEL:
                messageEntity.setKernel(resolveKernel(value));
                break;
            case PAN:
                messageEntity.setCardNumber(resolveCardNumber(value, messageEntity.getKernel()));
                break;
            case AMOUNT:
                messageEntity.setAmount(resolveAmount(value));
                break;
            case CURRENCY:
                messageEntity.setCurrency(resolveCurrency(value));
                break;
            default:
                // do nothing
        }
    }

    public static int getMessageLength(int currentIndex, String message) {
        return Integer.parseInt(message.substring(currentIndex, currentIndex + 2), 16) * 2;
    }
}
