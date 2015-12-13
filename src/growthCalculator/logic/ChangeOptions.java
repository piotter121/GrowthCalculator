package growthCalculator.logic;

import growthCalculator.growthCharts.GrowthChart;
import growthCalculator.growthCharts.factories.HeightGrowthChartsFactory;
import growthCalculator.gui.OptionsFrame;

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