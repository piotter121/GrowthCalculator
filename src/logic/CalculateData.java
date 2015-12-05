package logic;

import data.CalculatedData;
import data.UserData;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class CalculateData {
    private ShowAllData showAllData;

    public CalculateData(ShowAllData showAllDataController) {
        showAllData = showAllDataController;
    }

    public void calculateData(UserData userData) {
        showAllData.show(Options.sex, userData, new CalculatedData());
    }
}
