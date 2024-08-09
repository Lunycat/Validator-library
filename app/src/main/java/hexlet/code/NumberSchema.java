package hexlet.code;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        rules.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        rules.put("positive", i -> i > 0);
        return this;
    }

    public NumberSchema range(int x, int y) {
        rules.put("positive", i -> x <= i && i <= y);
        return this;
    }
}
