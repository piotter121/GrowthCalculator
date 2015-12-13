package growthCalculator.data;

import java.util.HashMap;

/**
 * GrowthCalculator
 * Created by Piotrek on 03-12-2015.
 */
public class UserData{
    private HashMap<Integer,Double> data;

    public UserData() {
        data = new HashMap<>();
    }

    public double getData(int age) {
        return data.get(age);
    }

    public boolean isSet() {
        return !data.isEmpty();
    }

    public void add(int age, double value) {
        data.put(age, value);
    }

    public Integer[] getAges() {
        return data.keySet().toArray(new Integer[data.size()]);
    }
}
