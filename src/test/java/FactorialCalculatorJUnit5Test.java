import com.example.lesson14_junit5.FactorialCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorJUnit5Test {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    @DisplayName("Факториал 0 равен 1")
    void testCalculateFactorialOfZero() {
        assertEquals(1, calculator.calculateFactorial(0));
    }

    @Test
    @DisplayName("Факториал 5 равен 120")
    void testCalculateFactorialOfPositiveNumber() {
        assertEquals(120, calculator.calculateFactorial(5));
    }

    @Test
    @DisplayName("Факториал 20 равен 2432902008176640000")
    void testCalculateFactorialOfLargeNumber() {
        assertEquals(2432902008176640000L, calculator.calculateFactorial(20));
    }

    @Test
    @DisplayName("Факторила отрицательного числа выбрасывает исключение")
    void testCalculateFactorialOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFactorial(-1));
    }
}