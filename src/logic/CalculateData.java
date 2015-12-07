package logic;

import data.CalculatedData;
import data.UserData;
import growthCharts.GrowthChart;

import java.util.ArrayList;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class CalculateData {
    private ShowAllData showAllData;

    private CalculatedData calculatedData;

    public CalculateData(ShowAllData showAllDataController) {
        showAllData = showAllDataController;
        calculatedData = new CalculatedData();
    }

    public void calculateData(UserData userData) {
        Integer userDataCount = userData.getCount();
        Integer[] ages = userData.getAges();
        GrowthChart chart = Options.getSex();
        ArrayList<Integer> matchedPercentiles = new ArrayList<>();

        for (Integer age: ages) {
            double value = userData.getData(age);
            matchedPercentiles.add(chart.matchToPercentile(age, value));
        }

        double averagePercentile = 0;
        for (int matchedPercentil: matchedPercentiles) averagePercentile += matchedPercentil;
        averagePercentile /= matchedPercentiles.size();
        createCalculatedData(averagePercentile, ages[ages.length-1], chart);

        showAllData.show(Options.getSex(), userData, calculatedData);
    }

    private void createCalculatedData(double averagePercentile, int startPoint, GrowthChart chart) {
        for (int iterator = startPoint + 1; iterator <= 18; iterator++) {

            //calculatedData.add(iterator,);
        }
    }

    public CalculatedData getCalculatedData() {
        return calculatedData;
    }
}
