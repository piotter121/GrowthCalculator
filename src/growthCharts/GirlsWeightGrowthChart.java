package growthCharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class GirlsWeightGrowthChart implements GrowthChart {
    private static final LinkedHashMap<Integer,ArrayList<Double>> data = makeChart();

    private static LinkedHashMap<Integer, ArrayList<Double>> makeChart() {
        LinkedHashMap<Integer, ArrayList<Double>> createdChartData = new LinkedHashMap<>(7);
        int centyl;
        ArrayList<Double> chartData;
        Double[] myData;

        centyl = 3;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                8.0,
                10.0,
                12.0,
                13.0,
                15.0,
                16.0,
                18.0,
                20.0,
                22.0,
                24.5,
                27.0,
                30.0,
                35.0,
                39.0,
                42.0,
                44.5,
                46.5,
                47.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 10;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                8.5,
                10.5,
                12.5,
                14.0,
                16.0,
                17.5,
                19.5,
                21.5,
                23.5,
                26.0,
                29.0,
                33.0,
                38.0,
                42.0,
                45.0,
                47.0,
                48.5,
                49.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 25;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                9.0,
                11.0,
                13.5,
                15.0,
                17.0,
                19.0,
                21.0,
                23.0,
                26.0,
                29.0,
                33.0,
                37.0,
                42.0,
                46.0,
                49.0,
                51.0,
                52.0,
                53.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 50;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                9.5,
                12.0,
                14.5,
                16.5,
                19.0,
                21.0,
                23.5,
                26.0,
                29.0,
                33.0,
                37.0,
                42.0,
                46.5,
                51.0,
                54.0,
                56.0,
                56.5,
                57.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 75;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                10.3,
                13.0,
                16.0,
                18.0,
                20.0,
                23.0,
                26.0,
                30.0,
                33.5,
                38.0,
                44.0,
                49.0,
                53.0,
                56.0,
                59.0,
                60.0,
                60.5,
                61.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 90;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                11.0,
                14.5,
                17.0,
                19.0,
                22.0,
                26.0,
                30.0,
                34.0,
                39.0,
                45.0,
                50.5,
                56.0,
                60.0,
                63.0,
                65.0,
                66.5,
                67.5,
                68.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 97;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                11.8,
                15.5,
                18.0,
                21.0,
                24.0,
                29.0,
                34.0,
                39.0,
                45.0,
                50.0,
                57.0,
                62.0,
                67.0,
                69.5,
                71.0,
                72.0,
                72.0,
                72.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        return createdChartData;
    }

    @Override
    public double getValueAt(int age, int centyl) {
        if (age > 0 && age < 19 && getCentylList().contains(centyl))
            return data.get(centyl).get(age-1);
        else throw new IllegalArgumentException();
    }

    @Override
    public List<Integer> getCentylList() {
        return Arrays.asList((Integer [])(data.keySet().toArray()));
    }
}
