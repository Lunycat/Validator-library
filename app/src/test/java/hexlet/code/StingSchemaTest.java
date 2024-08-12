package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StingSchemaTest {

    private StringSchema stringSchema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    void testRequired() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));

    }

    @Test
    void testContains() {
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(stringSchema.isValid("what does the fox say"));
    }

    @Test
    void testMinLength() {
        assertTrue(stringSchema.minLength(10).minLength(4).isValid("Hexlet"));
        assertTrue(stringSchema.minLength(6).isValid("Hexlet"));
    }
}
