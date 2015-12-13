package tests.growthChartsTests;

import growthCalculator.growthCharts.BoysHeightGrowthChart;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 07-12-2015.
 */
public class BoysHeightGrowthChartTest {

    private BoysHeightGrowthChart instance;
    private double delta;

    @Before
    public void setUp() {
        instance = new BoysHeightGrowthChart();
        delta = Double.MIN_VALUE * Math.pow(10, 3);
    }

    @Test
    public void testMatchToPercentile() {
        double step = 0.25;
        for (double val = 50.0; val <= 73; val += step)
            assertEquals(3, instance.matchToPercentile(1,val), delta);

        for (double val = 82; val < 90; val += step)
            assertEquals(97, instance.matchToPercentile(1,val), delta);
    }
}