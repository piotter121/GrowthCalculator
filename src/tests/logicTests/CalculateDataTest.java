package tests.logicTests;

import growthCalculator.data.CalculatorData;
import growthCalculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.gui.GrowthTableModel;
import growthCalculator.logic.CalculateData;
import growthCalculator.logic.ShowAllData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 13-12-2015.
 */
public class CalculateDataTest {

    private CalculatorData userData;
    private CalculateData calculator;

    @Before
    public void setUp() throws Exception {
        userData = new CalculatorData();
        calculator = new CalculateData(new ShowAllData(new JPanel(), new GrowthTableModel(1)));
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