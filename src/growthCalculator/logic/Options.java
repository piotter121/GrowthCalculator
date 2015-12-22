package growthCalculator.logic;

import growthCalculator.data.growthCharts.GrowthChart;
import growthCalculator.data.growthCharts.factories.GrowthChartsFactory;
import growthCalculator.data.growthCharts.factories.HeightGrowthChartsFactory;

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