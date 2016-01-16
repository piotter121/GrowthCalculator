package growthCalculator.calculator.growthCharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class BoysWeightGrowthChart extends GrowthChart {

    private static final LinkedHashMap<Integer,ArrayList<Double>> data = makeChart();

    private static LinkedHashMap<Integer, ArrayList<Double>> makeChart() {
        LinkedHashMap<Integer, ArrayList<Double>> createdChartData = new LinkedHashMap<>(7);
        int centyl;
        ArrayList<Double> chartData;
        Double[] myData;

        centyl = 3;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                8.5,
                10.5,
                12.4,
                14.0,
                15.5,
                17.25,
                18.75,
                20.25,
                22.5,
                25.0,
                27.0,
                30.0,
                33.0,
                37.5,
                41.5,
                48.0,
                53.5,
                54.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 10;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                9.0,
                11.2,
                13.0,
                15.0,
                16.5,
                18.25,
                20.0,
                22.0,
                25.0,
                27.0,
                30.0,
                33.0,
                37.0,
                41.0,
                47.0,
                53.0,
                58.0,
                59.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 25;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                9.5,
                12.0,
                14.0,
                16.0,
                18.0,
                19.5,
                21.5,
                23.5,
                26.75,
                30.0,
                33.0,
                37.0,
                41.0,
                46.0,
                52.25,
                58.0,
                62.0,
                63.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 50;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                10.3,
                13.0,
                15.0,
                17.0,
                19.3,
                21.3,
                24.0,
                27.0,
                30.0,
                34.0,
                38.0,
                42.5,
                48.0,
                53.0,
                58.0,
                64.0,
                67.0,
                68.3
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 75;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                11.0,
                14.0,
                16.2,
                18.8,
                21.0,
                23.5,
                26.0,
                30.0,
                34.5,
                39.0,
                43.5,
                50.0,
                56.0,
                62.0,
                67.0,
                70.5,
                73.0,
                74.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 90;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                11.5,
                15.0,
                17.0,
                20.3,
                23.0,
                26.3,
                30.0,
                36.0,
                42.0,
                47.0,
                52.5,
                58.5,
                65.0,
                70.5,
                74.5,
                77.5,
                80.5,
                81.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 97;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                12.6,
                16.0,
                18.5,
                22.0,
                25.0,
                29.0,
                34.0,
                42.0,
                48.0,
                53.0,
                59.0,
                67.0,
                74.0,
                78.0,
                83.0,
                85.0,
                86.5,
                87.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        return createdChartData;
    }

    @Override
    public double getValueAt(int age, int percentile) throws IllegalArgumentException {
        if (age <= 0 || age >= 19 || !getPercentilesList().contains(percentile))
            throw new IllegalArgumentException("Żądana wartość nie istnieje");
        else return data.get(percentile).get(age - 1);
    }

    @Override
    public List<Integer> getPercentilesList() {
        return new ArrayList<>(data.keySet());
    }
}
