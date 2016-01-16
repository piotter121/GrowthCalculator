package growthCalculator.calculator.growthCharts;

import java.util.List;
import java.util.ListIterator;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public abstract class GrowthChart {
    /**
     * Metoda zwracająca wartość z siatki centylowej dla zadanego wieku i centyla
     */
    public abstract double getValueAt(int age, int percentile);

    /**
     * Metoda zwracająca obiekt List z centylami których dane znajdą się na siatce centylowej
     */
    public abstract List<Integer> getPercentilesList();

    /**
     * Metoda zwracająca centyl do którego pasuje zadana wartość i wiek
     */
    public int matchToPercentile(Integer age, double value) {
        List<Integer> percentiles = getPercentilesList();
        ListIterator<Integer> percentilesIterator = percentiles.listIterator();
        int matchedPercentile = 0;

        while (percentilesIterator.hasNext()) {
            int lowerPercentile = percentilesIterator.next();
            if (!percentilesIterator.hasNext()) break;
            int upperPercentile = percentilesIterator.next();
            percentilesIterator.previous();
            double lowerValue, upperValue;
            if ((lowerValue = getValueAt(age, lowerPercentile)) <= value
                    && (upperValue = getValueAt(age, upperPercentile)) >= value) {
                matchedPercentile = lowerPercentile + (int) Math.round((value - lowerValue)
                        / ((upperValue - lowerValue) / (upperPercentile - lowerPercentile)));
                break;
            }
        }

        return matchedPercentile;
    }
}
