package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    private final Map<String, BaseSchema<String>> mapRules = new HashMap<>();

    public MapSchema() {
        rules.put("required", map -> isBlank || map != null);
    }

    public MapSchema sizeof(int size) {
        rules.put("sizeof", map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> map) {
        for (String key : map.keySet()) {
            rules.put("shape", value -> {
                BaseSchema<String> bs = map.get(key);
                String check = value.get(key);
                return bs.isValid(check);
            });
        }
        return this;
    }

    public MapSchema required() {
        isBlank = false;
        return this;
    }
}
