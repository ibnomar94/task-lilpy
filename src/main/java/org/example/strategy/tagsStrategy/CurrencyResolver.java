package org.example.strategy.tagsStrategy;

import org.example.entity.MessageEntity;

import java.util.Currency;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.message.property.Constants.CURRENCY;

public class CurrencyResolver implements TagsResolver {
    private Map<Integer, Currency> currencies = null;

    public CurrencyResolver(){
        currencies = Currency.getAvailableCurrencies().stream().collect(Collectors.toMap(Currency::getNumericCode, Function.identity(), (key1, key2) -> key1));
    }

    @Override
    public boolean isValid(String tag) {
        return tag.equalsIgnoreCase(CURRENCY);
    }

    @Override
    public void resolve(String value, MessageEntity messageEntity) {
        messageEntity.setCurrency(resolveCurrency(value));
    }

    private String resolveCurrency(String currency) {
        int cur = Integer.parseInt(currency);

        if (currencies.containsKey(cur)) {
            return currencies.get(cur).getCurrencyCode();
        }

        return null;
    }
}
