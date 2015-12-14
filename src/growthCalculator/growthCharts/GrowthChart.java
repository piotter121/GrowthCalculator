package growthCalculator.growthCharts;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Integer> percentiles = getPercentilesList();
        ArrayList<Double> abs = new ArrayList<>(percentiles.size());
        ListIterator<Integer> iterator = percentiles.listIterator();
        int percentile = 0;

        while (iterator.hasNext()) {
            int lPer = iterator.next();
            if (!iterator.hasNext())
                break;
            int pPer = iterator.next();
            iterator.previous();
            double down, up;

            if ((down = getValueAt(age, lPer)) <= value && (up = getValueAt(age, pPer)) >= value) {
                percentile = lPer + findPercentile(value, down, up, pPer - lPer);
                break;
            }
        }

        return percentile;
    }

    private int findPercentile(double value, double down, double up, int range) {
        double scale = (up - down) / range;
        double diff = value - down;
        return (int) Math.round(diff / scale);
    }
}
