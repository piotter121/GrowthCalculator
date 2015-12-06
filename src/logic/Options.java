package logic;

import growthCharts.GrowthChart;
import growthCharts.factories.GrowthChartsFactory;
import growthCharts.factories.HeightGrowthChartsFactory;

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