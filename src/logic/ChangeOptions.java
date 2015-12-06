package logic;

import growthCharts.GrowthChart;
import growthCharts.factories.GrowthChartsFactory;
import growthCharts.factories.HeightGrowthChartsFactory;
import gui.OptionsFrame;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class ChangeOptions{
    private OptionsFrame frame;
    private GrowthChart chart;

    public ChangeOptions() {
        chart = new HeightGrowthChartsFactory().returnBoysGrowthChart();
    }

    public GrowthChart returnSelectedGrowthChart() {
        return chart;
    }
}