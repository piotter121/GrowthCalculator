package logic;

import data.CalculatedData;
import data.UserData;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class CalculateData {
    private ShowAllData showAllData;

    private CalculatedData calculatedData;

    public CalculateData(ShowAllData showAllDataController) {
        showAllData = showAllDataController;
    }

    public void calculateData(UserData userData) {
        Integer[] ages = userData.getAges();
        showAllData.show(Options.sex, userData, new CalculatedData());
    }

    public CalculatedData getCalculatedData() {
        return calculatedData;
    }
}
