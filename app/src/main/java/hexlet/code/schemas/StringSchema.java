package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema contains(String content) {
        rules.put("contains", text -> text.contains(content));
        return this;
    }

    public StringSchema minLength(int minLength) {
        rules.put("minLength", text -> text.length() >= minLength);
        return this;
    }

    public StringSchema required() {
        isBlank = false;
        return this;
    }
}
