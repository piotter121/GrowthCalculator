package growthCalculator.growthCharts.factories;

import growthCalculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.growthCharts.GirlsHeightGrowthChart;
import growthCalculator.growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class HeightGrowthChartsFactory implements GrowthChartsFactory {

    @Override
    public GrowthChart returnBoysGrowthChart() {
        return new BoysHeightGrowthChart();
    }

    @Override
    public GrowthChart returnGirlsGrowthChart() {
        return new GirlsHeightGrowthChart();
    }
}
