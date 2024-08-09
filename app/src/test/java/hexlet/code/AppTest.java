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
    void test() {
        numberSchema.isValid(5); // true

// Пока не вызван метод required(), null считается валидным
        numberSchema.isValid(null); // true
        numberSchema.positive().isValid(null); // true

        numberSchema.required();

        numberSchema.isValid(null); // false
        numberSchema.isValid(10); // true

// Потому что ранее мы вызвали метод positive()
        numberSchema.isValid(-10); // false
//  Ноль — не положительное число
        numberSchema.isValid(0); // false

        numberSchema.range(5, 10);

        numberSchema.isValid(5); // true
        numberSchema.isValid(10); // true
        numberSchema.isValid(4); // false
        numberSchema.isValid(11); // false
    }
}
