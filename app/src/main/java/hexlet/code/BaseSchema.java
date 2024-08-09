package hexlet.code;

import java.util.HashMap;
import java.util.Map;
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
}
