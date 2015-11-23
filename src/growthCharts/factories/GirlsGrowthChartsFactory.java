package growthCharts.factories;

import growthCharts.GirlsHeightGrowthChart;
import growthCharts.GirlsWeightGrowthChart;
import growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class GirlsGrowthChartsFactory implements GrowthChartsFactory {
    @Override
    public GrowthChart returnWeightChart() {
        return new GirlsWeightGrowthChart();
    }

    @Override
    public GrowthChart returnHeightChart() {
        return new GirlsHeightGrowthChart();
    }
}
