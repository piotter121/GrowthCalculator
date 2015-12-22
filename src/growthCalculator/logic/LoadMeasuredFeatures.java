package growthCalculator.logic;

import growthCalculator.data.CalculatorData;
import growthCalculator.exceptions.CalculationException;
import growthCalculator.gui.GrowthTableModel;
import growthCalculator.gui.LoadDataFrame;

import javax.swing.*;
import java.io.File;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class LoadMeasuredFeatures {
    private LoadDataFrame loadDataFrame;

    private CalculatorData userData;

    private CalculateFeatureFeatures calculator;

    public LoadMeasuredFeatures(CalculateFeatureFeatures calculator) {
        this.calculator = calculator;
        loadDataFrame = new LoadDataFrame(this);
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            loadDataFrame.pack();
            loadDataFrame.setVisible(true);
        });
    }

    public void loadData(GrowthTableModel tableModel) {
        userData = new CalculatorData();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double age = (double) tableModel.getValueAt(i, 0);
            double val = (double) tableModel.getValueAt(i, 1);
            if (age > 0 && age < 19 && val > 0) {
                try {
                    userData.add((int) Math.round(age), val);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void loadData(File file) {

    }

    public void sendToCalculator() {
        try {
            calculator.calculateAndShowData(userData);
        } catch (CalculationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            userData = new CalculatorData();
            loadDataFrame.purgeTable();
        }
    }
}
