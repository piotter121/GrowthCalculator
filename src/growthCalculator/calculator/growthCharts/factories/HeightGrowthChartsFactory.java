package growthCalculator.calculator.growthCharts.factories;

import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GirlsHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class HeightGrowthChartsFactory extends GrowthChartsFactory {

    @Override
    public GrowthChart returnBoysGrowthChart() {
        return new BoysHeightGrowthChart();
    }

    @Override
    public GrowthChart returnGirlsGrowthChart() {
        return new GirlsHeightGrowthChart();
    }
}
