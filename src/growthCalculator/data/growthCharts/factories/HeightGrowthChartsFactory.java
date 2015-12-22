package growthCalculator.data.growthCharts.factories;

import growthCalculator.data.growthCharts.BoysHeightGrowthChart;
import growthCalculator.data.growthCharts.GirlsHeightGrowthChart;
import growthCalculator.data.growthCharts.GrowthChart;

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
