package growthCalculator.calculator;

import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;
import growthCalculator.exceptions.CalculationException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-12-2015.
 */
public class Calculator extends Observable{
    private TreeMap<Integer, Double> userData;
    private TreeMap<Integer, Double> result;
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

    public TreeMap<Integer, Double> getUserData() {
        return userData;
    }

    public TreeMap<Integer, Double> getCalculationResult() {
        return result;
    }

    public boolean hasData() {
        return !userData.isEmpty();
    }

    public void set(int age, double value) throws IllegalArgumentException {
        if (!result.isEmpty()) cleanResult();
        if (age < 1) throw new IllegalArgumentException("Zbyt niski wiek - " + age);
        if (age > 18) throw new IllegalArgumentException("Zbyt wysoki wiek - " + age);
        if (value < 0) throw new IllegalArgumentException("Ujemna wartość " + value);
        if (value == 0) {
            remove(age);
            return;
        }

        userData.put(age, value);
        ArrayList<Double> values = new ArrayList<>(userData.values());
        for (int i = 0; i < (values.size() - 1); i++)
            if (values.get(i) > values.get(i + 1)) {
                userData.remove(age);
                throw new IllegalArgumentException("Wartości wprowadzone są niepoprawne");
            }

        setChanged();
        notifyObservers();
    }

    private void cleanResult() {
        result.clear();
        setChanged();
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
            result.put(startPoint, userData.get(startPoint));

            if (average < 3 || average > 97)
                throw new CalculationException("Rozwój dziecka nie mieści się w granicach między 3 a 97 centylem!");
            for (int i = ++startPoint; i < (startPoint + 5) && i < 19; i++) {
                result.put(i, findValue(i, average));
            }

            setChanged();
            notifyObservers();
        }
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
        userData.remove(age);
        result.remove(age);
        setChanged();
        notifyObservers();
    }
}
