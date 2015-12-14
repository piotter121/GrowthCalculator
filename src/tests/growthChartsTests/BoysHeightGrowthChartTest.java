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
        assertEquals(15, instance.matchToPercentile(18, 172), delta);
        assertEquals(83, instance.matchToPercentile(18, 185), delta);
    }
}