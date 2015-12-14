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

    public void loadData(CalculatorData data) {
        for (Integer age: data.getAges())
            userData.add(age, data.getValue(age));
        calculator.calculateData(userData);
    }
}
