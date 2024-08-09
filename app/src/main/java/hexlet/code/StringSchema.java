package hexlet.code;

import org.apache.commons.lang3.StringUtils;

public class StringSchema extends BaseSchema<String>{

    public StringSchema required() {
        rules.put("required", text -> !StringUtils.isBlank(text));
        return this;
    }

    public StringSchema contains(String content) {
        rules.put("contains", text -> StringUtils.contains(text, content));
        return this;
    }

    public StringSchema minLength(int minLength) {
        rules.put("minLength", text -> text.length() >= minLength);
        return this;
    }
}
