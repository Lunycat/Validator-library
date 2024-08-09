package hexlet.code;

import org.apache.commons.lang3.StringUtils;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        rules.put("required", i -> true);
        return this;
    }
}
