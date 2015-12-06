package logic;

import data.CalculatedData;
import data.UserData;
import growthCharts.GrowthChart;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
        Integer startPoint = ages[userDataCount - 1];
        GrowthChart chart = Options.getSex();
        ArrayList<Integer> matchedPercentiles = new ArrayList<>();

        for (Integer age: ages) {
            double value = userData.getData(age);
            matchedPercentiles.add(chart.matchToPercentile(age, value));
        }

        showAllData.show(Options.getSex(), userData, calculatedData);
    }

    private void createCalculatedData(double averageFactor, int startPoint, GrowthChart chart) {
        //for (int iterator = startPoint + 1; iterator <= 18; iterator++) {

        //}
    }

    public CalculatedData getCalculatedData() {
        return calculatedData;
    }
}
