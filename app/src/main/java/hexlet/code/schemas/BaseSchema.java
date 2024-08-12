package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> rules = new LinkedHashMap<>();
    protected boolean isBlank = true;

    public boolean isValid(T value) {
        return rules
                .values()
                .stream()
                .allMatch(r -> r.test(value));
    }
}
