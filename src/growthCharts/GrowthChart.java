package growthCharts;

import java.util.List;
import java.util.ListIterator;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public abstract class GrowthChart {
    public abstract double getValueAt(int age, int centyl);
    public abstract List<Integer> getPercentilesList();
    public int matchToPercentile(Integer age, double value) {
        List<Integer> percentiles = this.getPercentilesList();
        ListIterator<Integer> it = percentiles.listIterator();
        int percentile = 3;

        while (it.hasNext()) {
            percentile = it.next();
            double readValue = getValueAt(age, percentile);
            /*if (value > readValue) {
            } else if (value == readValue) {
                return percentile;
            } else {
                return it.previous();
            }*/
        }

        return percentile;
    }
}
