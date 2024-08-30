import com.example.lesson14_testng.FactorialCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    public void testFactorialOfZero()  {
        assertEquals(calculator.calculateFactorial(0), 1);
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(calculator.calculateFactorial(5), 120);
    }

    @Test
    public void testFactorialOfTwenty() {
        assertEquals(calculator.calculateFactorial(20), 2432902008176640000L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        calculator.calculateFactorial(-1);
    }

    @Test
    public void testFactorialIsFalse() {
        assertEquals(calculator.calculateFactorial(7), 5000);
    }
}