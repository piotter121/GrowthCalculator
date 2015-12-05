package data;

import java.util.HashMap;
import java.util.Set;

/**
 * GrowthCalculator
 * Created by Piotrek on 03-12-2015.
 */
public class UserData{
    private HashMap<Integer,Double> data;
    private boolean isSet;

    public UserData() {
        data = new HashMap<>();
        isSet = false;
    }

    public UserData(double[][] data) {
        this();
        setData(data);
    }

    public double getData(int age) {
        return data.get(age);
    }

    public boolean isSet() {
        return isSet;
    }

    public int getCount() {
        return data.keySet().size();
    }

    public void setData(double[][] dataToSet) {
        Double value;
        for (int i = 0; i < 18; i++) {
            if ((value = dataToSet[i][1]) > 0) {
                data.put(i,value);
            } // TO DO zrobić lepszą walidację danych wejściowych
        }
    }

    public Set<Integer> getAge() {
        return data.keySet();
    }
}
