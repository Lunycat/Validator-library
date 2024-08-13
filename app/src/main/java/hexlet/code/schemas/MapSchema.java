package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema sizeof(int size) {
        rules.put("sizeof", map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> data) {
        for (String key : data.keySet()) {
            rules.put("shape", map -> data.get(key).isValid(map.get(key)));
        }
        return this;
    }

    public MapSchema required() {
        isBlank = false;
        return this;
    }
}
