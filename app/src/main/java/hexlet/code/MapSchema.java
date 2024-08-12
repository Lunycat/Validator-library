package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    public MapSchema sizeOf(int size) {
        rules.put("sizeOf", map -> map.size() == size);
        return this;
    }

    @Override
    public MapSchema required() {
        return (MapSchema) super.required();
    }
}
