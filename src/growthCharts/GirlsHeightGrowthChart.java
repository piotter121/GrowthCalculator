package growthCharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-11-2015.
 */
public class GirlsHeightGrowthChart implements GrowthChart {
    private static final LinkedHashMap<Integer,ArrayList<Double>> data = makeChart();

    private static LinkedHashMap<Integer, ArrayList<Double>> makeChart() {
        LinkedHashMap<Integer, ArrayList<Double>> createdChartData = new LinkedHashMap<>(7);
        int centyl;
        ArrayList<Double> chartData;
        Double[] myData;

        centyl = 3;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                70.5,
                81.0,
                90.0,
                96.0,
                102.0,
                107.0,
                113.0,
                119.0,
                124.0,
                128.0,
                134.0,
                140.0,
                146.0,
                150.0,
                152.0,
                153.0,
                153.5,
                154.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 10;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                72.0,
                83.0,
                91.0,
                98.0,
                105.0,
                110.0,
                115.0,
                121.0,
                127.0,
                132.0,
                138.0,
                144.0,
                150.0,
                154.0,
                156.0,
                157.0,
                157.5,
                158.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 25;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                73.5,
                84.0,
                93.0,
                100.0,
                107.0,
                113.0,
                119.0,
                125.0,
                130.0,
                136.0,
                142.0,
                148.0,
                154.0,
                158.0,
                159.5,
                160.5,
                161.0,
                161.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 50;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                75.0,
                86.0,
                95.0,
                103.0,
                110.0,
                116.5,
                123.0,
                129.0,
                135.0,
                141.0,
                146.0,
                153.0,
                159.0,
                162.0,
                163.5,
                164.5,
                165.0,
                165.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 75;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                77.0,
                89.0,
                98.0,
                106.0,
                114.0,
                120.0,
                127.0,
                133.0,
                138.0,
                145.0,
                151.0,
                157.5,
                163.0,
                166.0,
                167.5,
                168.5,
                169.0,
                169.0
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 90;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                78.5,
                90.0,
                100.0,
                108.0,
                116.0,
                124.0,
                130.0,
                136.0,
                143.0,
                149.0,
                156.0,
                161.5,
                167.0,
                169.5,
                171.0,
                172.0,
                172.5,
                172.5
        };
        chartData.addAll(Arrays.asList(myData));
        createdChartData.put(centyl,chartData);

        centyl = 97;
        chartData = new ArrayList<>(18);
        myData = new Double[]{
                80.0,
                92.0,
                102.0,
                110.0,
                119.0,
                127.0,
                135.0,
                140.0,
                147.0,
                153.0,
                160.0,
                166.0,
                170.0,
                173.0,
                174.5,
                175.5,
                176.0,
                176.0
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
        return new ArrayList<>(data.keySet());
    }
}
