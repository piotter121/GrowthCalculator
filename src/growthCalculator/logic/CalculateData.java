package growthCalculator.logic;

import growthCalculator.data.CalculatedData;
import growthCalculator.data.UserData;
import growthCalculator.growthCharts.GrowthChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class CalculateData {
    private ShowAllData showAllData;

    private CalculatedData calculatedData;

    public CalculateData(ShowAllData showAllDataController) {
        calculatedData = new CalculatedData();
        showAllData = showAllDataController;
    }

    public void calculateData(UserData userData) {
        Integer[] ages = userData.getAges();
        GrowthChart chart = Options.getSex();
        ArrayList<Integer> matchedPercentiles = new ArrayList<>();

        for (Integer age: ages) {
            double value = userData.getData(age);
            matchedPercentiles.add(chart.matchToPercentile(age, value));
        }

        double averagePercentile = 0;
        for (int matchedPercentil: matchedPercentiles) averagePercentile += matchedPercentil;
        averagePercentile = averagePercentile / matchedPercentiles.size();
        createCalculatedData((int) Math.round(averagePercentile), ages[ages.length-1], chart);

        showAllData.show(Options.getSex(), userData, calculatedData);
    }

    private void createCalculatedData(int averagePercentile, int startPoint, GrowthChart chart) {
        List<Integer> percentiles = chart.getPercentilesList();
        ArrayList<Integer> abs = new ArrayList<>(percentiles.size());
        for (int percentile: percentiles) abs.add(Math.abs(averagePercentile - percentile));
        int closestPercentile = percentiles.get(abs.indexOf(Collections.min(abs)));
        double factor = ((double) averagePercentile)/((double) closestPercentile);
        for (int ageIterator = startPoint + 1; ageIterator <= 18; ageIterator++)
            calculatedData.add(ageIterator, factor * chart.getValueAt(ageIterator, closestPercentile));
    }

    public CalculatedData getCalculatedData() {
        return calculatedData;
    }
}
