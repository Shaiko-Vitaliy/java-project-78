package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private final int numPositive = 22;
    private final int numNegative = -22;
    private final int numOutOfRange = 100;
    private final int minRange = -11;
    private final int maxRange = 22;

    @Test
    public void numberSchemaTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();
        assertTrue(numberSchema.isValid("string"));
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(numNegative));

        numberSchema.required();
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(numPositive));
        assertTrue(numberSchema.isValid(numNegative));

        numberSchema.positive();
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(numNegative));
        assertTrue(numberSchema.isValid(numPositive));

        numberSchema.range(minRange, maxRange);
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(numNegative));
        assertFalse(numberSchema.isValid(numOutOfRange));
        assertTrue(numberSchema.isValid(numPositive));
    }

    @Test
    public void positiveTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema2 = validator.number();
        numberSchema2.positive();
        assertFalse(numberSchema2.isValid(numNegative));
        assertTrue(numberSchema2.isValid(null));
        assertTrue(numberSchema2.isValid("string"));
        assertTrue(numberSchema2.isValid(numPositive));
    }

    @Test
    public void rangeTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema3 = validator.number();
        numberSchema3.range(minRange, maxRange);
        assertFalse(numberSchema3.isValid(numOutOfRange));
        assertTrue(numberSchema3.isValid(null));
        assertTrue(numberSchema3.isValid("string"));
        assertTrue(numberSchema3.isValid(numPositive));
    }

    @Test
    public void positiveAndRangeTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema4 = validator.number();
        numberSchema4.positive().range(minRange, maxRange);
        assertFalse(numberSchema4.isValid(numNegative));
        assertFalse(numberSchema4.isValid(numOutOfRange));
        assertTrue(numberSchema4.isValid(null));
        assertTrue(numberSchema4.isValid("string"));
        assertTrue(numberSchema4.isValid(numPositive));
    }
}
