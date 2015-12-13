package growthCalculator.growthCharts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public abstract class GrowthChart {
    public abstract double getValueAt(int age, int centyl);
    public abstract List<Integer> getPercentilesList();
    public int matchToPercentile(Integer age, double value) {
        List<Integer> percentiles = getPercentilesList();
        ArrayList<Double> abs = new ArrayList<>(percentiles.size());

        for (int percentile: percentiles)
            abs.add(Math.abs(getValueAt(age,percentile) - value));

        return percentiles.get(abs.indexOf(Collections.min(abs)));
    }
}
