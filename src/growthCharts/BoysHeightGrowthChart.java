package growthCharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class BoysHeightGrowthChart implements GrowthChart {
    private static final LinkedHashMap<Integer,ArrayList<Double>> data = makeChart();

    private static LinkedHashMap<Integer, ArrayList<Double>> makeChart() {
        LinkedHashMap<Integer, ArrayList<Double>> createdChartData = new LinkedHashMap<>(7);
        int centyl;
        ArrayList<Double> chartData;
        Double[] myData;

        centyl = 3;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                72.0,
                84.0,
                90.0,
                97.0,
                104.0,
                110.0,
                115.0,
                120.0,
                124.0,
                128.5,
                134.0,
                140.0,
                145.0,
                151.0,
                158.0,
                165.0,
                166.5,
                167.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 10;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                74.0,
                85.0,
                92.0,
                99.0,
                106.0,
                112.0,
                118.0,
                123.0,
                128.0,
                133.0,
                138.0,
                143.5,
                149.0,
                155.0,
                163.0,
                168.0,
                170.0,
                170.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 25;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                75.0,
                87.0,
                94.5,
                101.0,
                108.0,
                114.5,
                121.0,
                126.0,
                132.0,
                137.0,
                142.0,
                147.0,
                153.5,
                160.0,
                167.0,
                172.0,
                175.0,
                175.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 50;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                77.0,
                88.5,
                97.0,
                105.0,
                111.0,
                117.5,
                124.0,
                129.5,
                135.0,
                141.0,
                146.0,
                152.0,
                159.0,
                166.0,
                171.5,
                176.0,
                178.5,
                179.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 75;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                79.0,
                90.0,
                99.0,
                107.0,
                114.0,
                121.0,
                127.0,
                133.0,
                139.0,
                145.0,
                150.0,
                157.0,
                165.0,
                172.0,
                176.5,
                180.5,
                182.5,
                183.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 90;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                80.0,
                92.0,
                100.0,
                110.0,
                117.0,
                125.0,
                130.5,
                137.0,
                142.5,
                149.0,
                155.0,
                162.5,
                170.0,
                176.0,
                181.0,
                185.0,
                187.0,
                187.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 97;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                82.0,
                94.0,
                103.0,
                113.0,
                120.0,
                128.5,
                134.5,
                140.0,
                147.0,
                153.5,
                159.0,
                167.0,
                175.0,
                182.0,
                185.0,
                189.0,
                190.0,
                190.5
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
