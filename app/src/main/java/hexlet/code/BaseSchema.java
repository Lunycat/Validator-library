package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> rules;

    public BaseSchema() {
        rules = new HashMap<>();
    }

    public boolean isValid(T value) {
        for (Predicate<T> predicate : rules.values()) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }

    public BaseSchema<T> required() {
        rules.put("required", this::isBlank);
        return this;
    }

    private boolean isBlank(T value) {
        if (value instanceof String && value.equals("")) {
            value = null;
        }
        return Objects.nonNull(value);
    }
}
