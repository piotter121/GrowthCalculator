package growthCalculator.growthCharts.factories;

import growthCalculator.growthCharts.BoysWeightGrowthChart;
import growthCalculator.growthCharts.GirlsWeightGrowthChart;
import growthCalculator.growthCharts.GrowthChart;

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
