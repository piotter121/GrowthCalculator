package growthCalculator.logic;

import growthCalculator.data.growthCharts.GrowthChart;
import growthCalculator.data.growthCharts.factories.HeightGrowthChartsFactory;
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