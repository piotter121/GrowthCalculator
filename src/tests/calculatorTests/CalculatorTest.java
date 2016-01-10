package tests.calculatorTests;

import growthCalculator.calculator.Calculator;
import growthCalculator.exceptions.NonGrowingDataOrderException;
import org.junit.Before;
import org.junit.Test;

import java.util.SortedMap;

import static org.junit.Assert.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 27-12-2015.
 */
public class CalculatorTest {
    private Calculator calculator;
    private double delta = 1.0E-03;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void CalculationTest1() throws Exception {
        calculator.set(9, 140);
        calculator.set(8, 130);
        calculator.calculate();
        SortedMap<Integer, Double> result = calculator.getCalculationResult();
        Integer[] ages = new Integer[] {10, 11, 12, 13, 14};
        Double[] values = new Double[] {143.72, 148.72, 155.4, 163.08, 170.08};
        for (int i = 0; i < ages.length; i++) {
            assertEquals(values[i], result.get(ages[i]), delta);
        }
    }

    @Test
    public void hasNoDataTest() throws Exception {
        assertFalse(calculator.hasData());
    }

    @Test
    public void hasDataTest1() throws Exception {
        calculator.set(10, 130);
        assertTrue(calculator.hasData());
    }

    @Test
    public void hasDataTest2() throws Exception {
        calculator.set(8, 130);
        calculator.calculate();
        assertTrue(calculator.hasData());
    }

    @Test
    public void settingMinimumAgeWithValueTest() throws Exception {
        calculator.set(1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingToSmallAgeWithValueTest() throws Exception {
        calculator.set(0, 100);
    }

    @Test
    public void settingMaximumAgeWithValueTest() throws Exception {
        calculator.set(18, 190);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingToHighAgeWithValueTest() throws Exception {
        calculator.set(19, 200);
    }

    @Test
    public void settingMinimumValueTest() throws Exception {
        calculator.set(10, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNonPositiveValueTest() throws Exception {
        calculator.set(10, -1.0);
    }

    @Test(expected = NonGrowingDataOrderException.class)
    public void settingDataInNonGrowingOrderTest() throws Exception {
        calculator.set(8, 130);
        calculator.set(9, 129);
    }

    @Test
    public void removingDataTest1() throws Exception {
        calculator.set(10, 120);
        calculator.remove(10);
        assertFalse(calculator.hasData());
        assertFalse(calculator.getUserData().containsKey(10));
    }
}