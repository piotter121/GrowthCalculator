package controllers;

import growthCharts.BoysHeightGrowthChart;
import growthCharts.GrowthChart;
import growthCharts.factories.BoysGrowthChartsFactory;
import growthCharts.factories.GrowthChartsFactory;
import gui.OptionsFrame;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class ChangeOptions{
    private OptionsFrame frame;
    private GrowthChart chart;

    public ChangeOptions() {
        chart = new BoysGrowthChartsFactory().returnHeightChart();
    }

    public GrowthChart returnSelectedGrowthChart() {
        return chart;
    }
}
