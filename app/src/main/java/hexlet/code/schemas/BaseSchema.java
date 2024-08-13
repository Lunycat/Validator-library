package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> rules = new HashMap<>();
    protected boolean isBlank = true;

    public final boolean isValid(T value) {
        if (value == null || Objects.equals(value, "")) {
            return isBlank;
        }
        return rules
                .values()
                .stream()
                .allMatch(r -> r.test(value));
    }
}
