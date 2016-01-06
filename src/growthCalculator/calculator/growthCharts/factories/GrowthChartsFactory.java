package growthCalculator.calculator.growthCharts.factories;

import growthCalculator.calculator.growthCharts.GrowthChart;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public abstract class GrowthChartsFactory {
    private static GrowthChartsFactory current = new HeightGrowthChartsFactory();

    public static GrowthChart BoysGrowthChart() {
        return current.returnBoysGrowthChart();
    }

    public static GrowthChart GirlsGrowthChart() {
        return current.returnGirlsGrowthChart();
    }

    public static void setHeightFactory() {
        current = new HeightGrowthChartsFactory();
    }

    public static void setWeightFactory() {
        current = new WeightGrowthChartsFactory();
    }

    public abstract GrowthChart returnBoysGrowthChart();
    public abstract GrowthChart returnGirlsGrowthChart();
}
