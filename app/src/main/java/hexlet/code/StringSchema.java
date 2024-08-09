package hexlet.code;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {

    private final Map<String, Predicate<String>> rules;
    private int minLength;
    private String content = "";

    public StringSchema() {
        rules = new HashMap<>(Map.of(
                "required", text -> true,
                "minLength", text -> text.length() >= minLength,
                "contains", text -> StringUtils.contains(text, content)));
    }

    public boolean isValid(String text) {
        if (text == null) {
            text = "";
        }

        for (Predicate<String> predicate : rules.values()) {
            if (!predicate.test(text)) {
                return false;
            }
        }
        return true;
    }

    public StringSchema required() {
        rules.put("required", text -> !StringUtils.isBlank(text));
        return this;
    }

    public StringSchema contains(String text) {
        content = text;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }
}
