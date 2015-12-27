package growthCalculator.calculator.growthCharts.factories;

import growthCalculator.calculator.growthCharts.BoysWeightGrowthChart;
import growthCalculator.calculator.growthCharts.GirlsWeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;

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
