package growthCalculator.logic;

import growthCalculator.data.CalculatorData;
import growthCalculator.gui.LoadDataFrame;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class LoadData{
    private final JFrame loadDataFrame;

    private CalculatorData userData;

    private CalculateData calculator;

    public LoadData(CalculateData calculator) {
        this.calculator = calculator;
        loadDataFrame = new LoadDataFrame(this);
        userData = new CalculatorData();
    }

    public void start() {
        SwingUtilities.invokeLater(() -> loadDataFrame.setVisible(true));
    }

    public void loadData(double[][] data) {
        for (int i = 0; i < 18; i++)
            if (data[i][1] > 0)
                userData.add(i+1, data[i][1]);
        calculator.calculateData(userData);
    }
}
