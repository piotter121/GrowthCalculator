package growthCharts;

import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public interface GrowthChart {
    double getValueAt(int age, int centyl);
    List<Integer> getCentylList();
}
