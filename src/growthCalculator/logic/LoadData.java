package growthCalculator.logic;

import growthCalculator.data.CalculatorData;
import growthCalculator.gui.LoadDataFrame;

import javax.swing.*;
import java.io.File;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class LoadData{
    private JFrame loadDataFrame;

    private CalculatorData userData;

    private CalculateData calculator;

    public LoadData(CalculateData calculator) {
        this.calculator = calculator;
        userData = new CalculatorData();
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            loadDataFrame = new LoadDataFrame(LoadData.this);
            loadDataFrame.pack();
            loadDataFrame.setVisible(true);
        });
    }

    public void loadData(CalculatorData userData) {
        this.userData = userData;
    }

    public void loadData(File file) {

    }

    public void sendToCalculator() {
        calculator.calculateAndShowData(userData);
    }

    public CalculatorData getUserData() {
        return userData;
    }
}
