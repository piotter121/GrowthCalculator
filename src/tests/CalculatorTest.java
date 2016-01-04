package tests;

import growthCalculator.calculator.Calculator;
import growthCalculator.logic.SaveDataToFile;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

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
        new SaveDataToFile(calculator.getCalculationResult()).actionPerformed(new ActionEvent(new Object(), 1, "pl"));
    }
}