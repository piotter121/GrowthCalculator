package growthCalculator.logic;

import growthCalculator.data.CalculatorData;
import growthCalculator.growthCharts.GrowthChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class CalculateData {
    private ShowAllData showAllData;

    private CalculatorData calculatedData;

    public CalculateData(ShowAllData showAllDataController) {
        calculatedData = new CalculatorData();
        showAllData = showAllDataController;
    }

    public void calculateData(CalculatorData userData) {
        Integer[] ages = userData.getAges();
        GrowthChart chart = Options.getSex();
        int matchedPercentiles = 0;
        int counter = 0;

        for (Integer age: ages) {
            double value = userData.getValue(age);
            matchedPercentiles += chart.matchToPercentile(age, value);
            counter++;
        }
        int average = (int) Math.round(((double) matchedPercentiles) / ((double) counter));
        int startPoint = ages[ages.length-1];
        calculatedData.add(startPoint, userData.getValue(startPoint));
        createCalculatedData(average, ++startPoint, chart);

        showAllData.show(Options.getSex(), userData, calculatedData);
    }

    private void createCalculatedData(int avarage, int startPoint, GrowthChart chart) {
        if (avarage < 3 || avarage > 97 || startPoint < 1 || startPoint > 18)
            throw new IllegalArgumentException();
        for (int i = startPoint; i < (startPoint + 3) && i < 19; i++)
            calculatedData.add(i, findValue(i, avarage, chart));
    }

    private double findValue(int age, int average, GrowthChart chart) {
        List<Integer> percentiles = chart.getPercentilesList();
        ArrayList<Integer> abs = new ArrayList<>(percentiles.size());
        abs.addAll(percentiles.stream().map(percentile -> Math.abs(percentile - average)).collect(Collectors.toList()));
        int index;
        int matched = percentiles.get((index = abs.indexOf(Collections.min(abs))));
        int upperPercentile, lowerPercentile;
        if (average >= matched && matched != percentiles.get(percentiles.size()-1)) {
            lowerPercentile = matched;
            upperPercentile = percentiles.get(index + 1);
        } else {
            upperPercentile = matched;
            lowerPercentile = percentiles.get(index - 1);
        }
        double lowerValue = chart.getValueAt(age, lowerPercentile);
        int percentilesRange = upperPercentile - lowerPercentile;
        double valueRange = chart.getValueAt(age, upperPercentile) - lowerValue;
        double valuesPerPercentile = valueRange / (double) percentilesRange;
        return lowerValue + valuesPerPercentile * (average - lowerPercentile);
    }

    public CalculatorData getCalculatedData() {
        return calculatedData;
    }
}
