package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppTest {

    private StringSchema stringSchema;
    private NumberSchema numberSchema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
    }

    @Test
    void testRequiredStringSchema() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));

    }

    @Test
    void testContainsStringSchema() {
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(stringSchema.isValid("what does the fox say"));
    }

    @Test
    void testMinLengthStringSchema() {
        assertTrue(stringSchema.minLength(10).minLength(4).isValid("Hexlet"));
        assertTrue(stringSchema.minLength(6).isValid("Hexlet"));
    }

    @Test
    void testRequiredNumberSchema() {
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(null));

        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(10));
    }

    @Test
    void testPositiveNumberSchema() {
        assertTrue(numberSchema.isValid(0));
        assertTrue(numberSchema.isValid(-10));

        numberSchema.positive();

        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(-10));
    }

    @Test
    void testRangeNumberSchema() {
        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));
    }

    @Test
    void testFluentNumberSchema() {
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(-10));

        assertFalse(numberSchema.required().positive().isValid(0));
        assertTrue(numberSchema.isValid(1));

        assertFalse(numberSchema.range(5, 10).isValid(4));
        assertTrue(numberSchema.isValid(10));
    }
}
