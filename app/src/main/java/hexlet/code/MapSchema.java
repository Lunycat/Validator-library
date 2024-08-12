package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {

    private final Map<String, BaseSchema<String>> mapRules = new HashMap<>();

    public MapSchema sizeOf(int size) {
        rules.put("sizeOf", map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> rule) {
        mapRules.putAll(rule);
        return this;
    }

    @Override
    public MapSchema required() {
        return (MapSchema) super.required();
    }

    @Override
    public boolean isValid(Map<String, String> value) {
        boolean isValidBase = super.isValid(value);
        boolean isValidMap = true;

        for (String key : mapRules.keySet()) {
            BaseSchema<String> baseSchema = mapRules.get(key);
            String check = value.get(key) != null ? value.get(key) : "";

            if (!baseSchema.isValid(check)) {
                isValidMap = false;
            }
        }

        return isValidBase && isValidMap;
    }
}
