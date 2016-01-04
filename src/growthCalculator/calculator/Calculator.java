package growthCalculator.calculator;

import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;
import growthCalculator.exceptions.CalculationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-12-2015.
 */
public class Calculator extends Observable{
    private SortedMap<Integer, Double> userData;
    private SortedMap<Integer, Double> result;
    private GrowthChart chart;

    public Calculator() {
        userData = new TreeMap<>(Integer::compareTo);
        result = new TreeMap<>(Integer::compareTo);
        chart = new BoysHeightGrowthChart();
    }

    public void setGrowthChart(GrowthChart chart) {
        this.chart = chart;
        setChanged();
        notifyObservers(chart);
    }

    public GrowthChart getGrowthChart() {
        return chart;
    }

    public SortedMap<Integer, Double> getUserData() {
        return userData;
    }

    public SortedMap<Integer, Double> getCalculationResult() {
        return result;
    }

    public boolean hasData() {
        return !userData.isEmpty() || !result.isEmpty();
    }

    public void set(int age, double value) throws IllegalArgumentException {
        if (age < 1) throw new IllegalArgumentException("Zbyt niski wiek - " + age);
        if (age > 18) throw new IllegalArgumentException("Zbyt wysoki wiek - " + age);
        if (value <= 0) throw new IllegalArgumentException("Niedodatnia wartość " + value);

        userData.put(age, value);
        ArrayList<Double> values = new ArrayList<>(userData.values());
        for (int i = 0; i < (values.size() - 1); i++)
            if (values.get(i) > values.get(i + 1)) {
                userData.remove(age);
                throw new IllegalArgumentException("Wartości wprowadzone są niepoprawne");
            }

        notifyObservers();
    }

    public void calculate() throws CalculationException {
        if (hasData() && result.isEmpty()) {
            Integer[] ages = userData.keySet().toArray(new Integer[userData.size()]);
            int matchedPercentiles = 0;
            int counter = 0;

            for (Integer age : ages) {
                double value = userData.get(age);
                matchedPercentiles += chart.matchToPercentile(age, value);
                counter++;
            }
            int average = (int) Math.round(((double) matchedPercentiles) / ((double) counter));
            int startPoint = ages[ages.length - 1];

            if (average < 3 || average > 97)
                throw new CalculationException("Rozwój dziecka nie mieści się w granicach między 3 a 97 centylem!");
            for (int i = ++startPoint; i < (startPoint + 5) && i < 19; i++) {
                setResult(i, findValue(i, average));
            }
        }
    }

    private void setResult(int age, double val) {
        result.put(age, val);
        notifyObservers();
    }

    private double findValue(int age, int average) {
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
        double percentilesRange = upperPercentile - lowerPercentile;
        double valueRange = chart.getValueAt(age, upperPercentile) - lowerValue;
        double valuesPerPercentile = valueRange / percentilesRange;
        return lowerValue + valuesPerPercentile * (average - lowerPercentile);
    }

    public void remove(int age) {
        if (userData.containsKey(age))
            userData.remove(age);
        if (result.containsKey(age))
            result.remove(age);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
