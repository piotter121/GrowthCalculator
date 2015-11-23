package growthCharts.factories;

import growthCharts.BoysHeightGrowthChart;
import growthCharts.BoysWeightGrowthChart;
import growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class BoysGrowthChartsFactory implements GrowthChartsFactory {
    @Override
    public GrowthChart returnWeightChart() {
        return new BoysWeightGrowthChart();
    }

    @Override
    public GrowthChart returnHeightChart() {
        return new BoysHeightGrowthChart();
    }
}
