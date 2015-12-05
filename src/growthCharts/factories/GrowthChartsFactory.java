package growthCharts.factories;

import growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public interface GrowthChartsFactory {
    GrowthChart returnBoysGrowthChart();
    GrowthChart returnGirlsGrowthChart();
}
