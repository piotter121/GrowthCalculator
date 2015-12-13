package tests.logicTests;

import growthCalculator.data.UserData;
import growthCalculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.logic.CalculateData;
import growthCalculator.logic.ShowAllData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 13-12-2015.
 */
public class CalculateDataTest {

    private UserData userData;
    private CalculateData calculator;

    @Before
    public void setUp() throws Exception {
        userData = new UserData();
        calculator = new CalculateData(new ShowAllData(new JPanel(),new JTable()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateData() throws Exception {
        userData.add(8, 131.5);
        userData.add(9, 137);
        System.out.println(new BoysHeightGrowthChart().matchToPercentile(8, 131.5));
        System.out.println(new BoysHeightGrowthChart().matchToPercentile(9, 137));
        System.out.println(Math.round(62.5));
        calculator.calculateData(userData);
        System.out.println(userData);
        System.out.println(calculator.getCalculatedData());
    }
}