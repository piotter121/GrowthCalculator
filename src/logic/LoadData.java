package logic;

import data.UserData;
import gui.LoadDataFrame;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class LoadData{
    private final JFrame loadDataFrame;

    private UserData userData;

    private CalculateData calculator;

    public LoadData(CalculateData calculator) {
        this.calculator = calculator;
        loadDataFrame = new LoadDataFrame(this);

    }

    public void start() {
        SwingUtilities.invokeLater(() -> loadDataFrame.setVisible(true));
    }

    public void loadData(double[][] data) {
        userData = new UserData(data);
        calculator.calculateData(userData);
    }
}
