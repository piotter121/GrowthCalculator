package growthCalculator.data;

import java.util.HashMap;

/**
 * GrowthCalculator
 * Created by Piotrek on 05-12-2015.
 */
public class CalculatorData {
    private HashMap<Integer, Double> values;

    public CalculatorData() {
        values = new HashMap<>();
    }

    public double getValue(int age) {
        if (!(values.keySet().contains(age))) throw new IllegalArgumentException();
        return values.get(age);
    }

    public boolean isSet() {
        return !values.isEmpty();
    }

    public void add(int age, double value) {
        values.put(age, value);
    }

    public Integer[] getAges() {
        return values.keySet().toArray(new Integer[values.size()]);
    }

    public String toString() {
        return values.toString();
    }
}
