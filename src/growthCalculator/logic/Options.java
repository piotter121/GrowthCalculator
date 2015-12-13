package growthCalculator.logic;

import growthCalculator.growthCharts.GrowthChart;
import growthCalculator.growthCharts.factories.GrowthChartsFactory;
import growthCalculator.growthCharts.factories.HeightGrowthChartsFactory;

public class Options {
    static GrowthChartsFactory factory = new HeightGrowthChartsFactory();
    static GrowthChart sex = factory.returnBoysGrowthChart();

    public static GrowthChartsFactory getFactory() {
        return factory;
    }

    public static GrowthChart getSex() {
        return sex;
    }
}