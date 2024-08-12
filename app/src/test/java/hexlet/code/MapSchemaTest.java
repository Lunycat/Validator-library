package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private MapSchema mapSchema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        mapSchema = validator.map();
    }

    @Test
    void testRequired() {
        assertTrue(mapSchema.isValid(null));

        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    void testSizeOf() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data));

        mapSchema.sizeOf(2);
        assertFalse(mapSchema.isValid(data));

        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }
}
