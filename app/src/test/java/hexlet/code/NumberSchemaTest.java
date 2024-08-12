package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class NumberSchemaTest {

    private NumberSchema numberSchema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        numberSchema = validator.number();
    }

    @Test
    void testRequired() {
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(null));

        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(10));
    }

    @Test
    void testPositive() {
        assertTrue(numberSchema.isValid(0));
        assertTrue(numberSchema.isValid(-10));

        numberSchema.positive();

        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(-10));
    }

    @Test
    void testRange() {
        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));
    }

    @Test
    void testFluent() {
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(-10));

        assertFalse(numberSchema.required().positive().isValid(0));
        assertTrue(numberSchema.isValid(1));

        assertFalse(numberSchema.range(5, 10).isValid(4));
        assertTrue(numberSchema.isValid(10));
    }
}
