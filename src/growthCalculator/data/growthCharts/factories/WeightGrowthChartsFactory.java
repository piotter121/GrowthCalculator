package growthCalculator.data.growthCharts.factories;

import growthCalculator.data.growthCharts.BoysWeightGrowthChart;
import growthCalculator.data.growthCharts.GirlsWeightGrowthChart;
import growthCalculator.data.growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class WeightGrowthChartsFactory implements GrowthChartsFactory {

    @Override
    public GrowthChart returnBoysGrowthChart() {
        return new BoysWeightGrowthChart();
    }

    @Override
    public GrowthChart returnGirlsGrowthChart() {
        return new GirlsWeightGrowthChart();
    }
}
