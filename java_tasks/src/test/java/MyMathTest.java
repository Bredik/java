import org.junit.Test;
import unitTests.MyMath;

/*
@Test — определение данного метода в качестве тестируемого
(по сути — метод, помеченный данной аннотацией и есть модульный тест).

@Test (expected = Exception.class) — используется для отрицательных тестов.
Это тесты, которые проверяют, как ведёт себя метод в случае ошибки, то есть
тест ожидает, что метод выкинет некоторое исключение.
 */

public class MyMathTest {

    @Test(expected = ArithmeticException.class)
    public void zeroDenominatorShouldThrowException() {
        //MyMath myMath = new MyMath();
        //myMath.divide(1, 0);
        MyMath.divide(1,0);
    }
}
