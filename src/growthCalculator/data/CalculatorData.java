package growthCalculator.data;

import java.util.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 05-12-2015.
 */
public class CalculatorData {
    private HashMap<Integer, Double> values;

    public CalculatorData() {
        values = new HashMap<>();
    }

    public double getValue(int age) throws IllegalArgumentException {
        if (!(values.keySet().contains(age))) throw new IllegalArgumentException();
        return values.get(age);
    }

    public boolean isSet() {
        return !values.isEmpty();
    }

    public void add(int age, double value) throws IllegalArgumentException {
        if (age < 1) throw new IllegalArgumentException("Zbyt niski wiek - " + age);
        if (age > 18) throw new IllegalArgumentException("Zbyt wysoki wiek - " + age);
        if (value < 0) throw new IllegalArgumentException("Ujemna wartość " + value);

        values.put(age, value);

        ArrayList<Integer> ages = new ArrayList<>(values.keySet());
        Collections.sort(ages);
        ListIterator<Integer> iterator = ages.listIterator(ages.indexOf(age));
        int i;
        double val;
        if (iterator.hasPrevious()) {
            i = iterator.previous();
            iterator.next();
            if ((val = getValue(i)) > value) {
                values.remove(age);
                throw new IllegalArgumentException("Podana wartość (" + age + "," + value
                        + ") jest mniejsza od poprzedzającej ją (" + i + "," + val + ").");
            }
        }
        if (iterator.hasNext()) {
            i = iterator.next();
            iterator.previous();
            if (value > (val = getValue(i))) {
                values.remove(age);
                throw new IllegalArgumentException("Podana wartość (" + age + "," + value
                        + ") jest większa od następujących po niej (" + i + "," + val + ").");
            }
        }
    }

    public Integer[] getAges() {
        return values.keySet().toArray(new Integer[values.size()]);
    }

    public String toString() {
        return values.toString();
    }
}
