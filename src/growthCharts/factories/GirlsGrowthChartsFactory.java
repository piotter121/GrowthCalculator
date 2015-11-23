package growthCharts.factories;

import growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class GirlsGrowthChartsFactory implements GrowthChartsFactory {
    @Override
    public GrowthChart returnWeightChart() {
        return null;
    }

    @Override
    public GrowthChart returnHeightChart() {
        return null;
    }
}
