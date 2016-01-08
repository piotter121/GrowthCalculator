package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.calculatorTests.CalculatorTest;

/**
 * GrowthCalculator
 * Created by Piotrek on 08-01-2016.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest.class
})

public class AllTestsSuite {
}
