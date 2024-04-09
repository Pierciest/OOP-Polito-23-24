package calc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCalculator {

    @Test
    public void testSum(){
        double a = 4.5;
        double b = 3.5;

        double result = Calculator.compute(Calculator.SUM, a, b);

        assertEquals("Wrong sum result", a+b, result, 0.01);
    }

    @Test
    public void testSub(){
        double a = 4.5;
        double b = 3.5;

        double result = Calculator.compute(Calculator.SUB, a, b);

        assertEquals("Wrong subtraction result", a-b, result, 0.01);
    }

    @Test
    public void testMul(){
        double a = 4.5;
        double b = 3.5;

        double result = Calculator.compute(Calculator.MUL, a, b);

        assertEquals("Wrong subtraction result", a*b, result, 0.01);
    }

    @Test
    public void testDiv(){
        double a = 4.5;
        double b = 1.5;

        double result = Calculator.compute(Calculator.DIV, a, b);

        assertEquals("Wrong subtraction result", a/b, result, 0.01);
    }

    @Test
    public void testDivByZero(){
        double a = 4.5;
        double b = 0;

        Calculator.compute(Calculator.DIV, a, b);

        assertTrue(Calculator.error);
    }

    @Test
    public void testWrongOp(){
        double a = 1;
        double b = 1;

        Calculator.compute(999, a, b);

        assertTrue(Calculator.error);
    }
}
