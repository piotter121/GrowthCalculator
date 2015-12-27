package tests;

import growthCalculator.calculator.Calculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 27-12-2015.
 */
public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void mainTest() throws Exception {
        calculator.set(9, 140);
        calculator.set(8, 130);
        System.out.println(calculator.getUserData());
        calculator.calculate();
        System.out.println(calculator.getCalculationResult());
    }
}