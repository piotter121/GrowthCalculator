package tests.logicTests;

import growthCalculator.data.CalculatorData;
import growthCalculator.data.growthCharts.BoysHeightGrowthChart;
import growthCalculator.logic.CalculateFeatureFeatures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * GrowthCalculator
 * Created by Piotrek on 13-12-2015.
 */
public class CalculateFeatureFeaturesTest {

    private CalculatorData userData;
    private CalculateFeatureFeatures calculator;

    @Before
    public void setUp() throws Exception {
        userData = new CalculatorData();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateData() throws Exception {
        userData.add(8, 128);
        userData.add(9, 137);
        System.out.println(new BoysHeightGrowthChart().matchToPercentile(8, 128));
        System.out.println(new BoysHeightGrowthChart().matchToPercentile(9, 137));
        calculator.calculateAndShowData(userData);
        System.out.println(userData);
        System.out.println(calculator.getCalculatedData());
    }
}