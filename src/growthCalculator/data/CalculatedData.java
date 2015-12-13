package growthCalculator.data;

import java.util.HashMap;

/**
 * GrowthCalculator
 * Created by Piotrek on 05-12-2015.
 */
public class CalculatedData {
    private HashMap<Integer, Double> values;

    public CalculatedData() {
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

    public void delete(int age) {
        values.remove(age);
    }

    public Integer[] getAges() {
        return values.keySet().toArray(new Integer[values.size()]);
    }
}
