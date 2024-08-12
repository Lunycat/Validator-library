package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema() {
        rules.put("required", num -> isBlank || num != null);
    }

    public NumberSchema positive() {
        rules.put("positive", num -> num > 0);
        return this;
    }

    public NumberSchema range(int x, int y) {
        rules.put("positive", num -> x <= num && num <= y);
        return this;
    }

    public NumberSchema required() {
        isBlank = false;
        return this;
    }
}
