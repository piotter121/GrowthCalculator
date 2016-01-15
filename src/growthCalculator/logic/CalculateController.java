package growthCalculator.logic;

import growthCalculator.calculator.GrowthCalculator;
import growthCalculator.exceptions.CalculationException;
import growthCalculator.exceptions.ExceptionsHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GrowthCalculator
 * Created by Piotrek on 04-01-2016.
 */
public class CalculateController implements ActionListener {
    private GrowthCalculator calculator;
    private JTable table;

    public CalculateController(GrowthCalculator calculator, JTable table) {
        this.table = table;
        this.calculator = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((JButton)e.getSource()).setEnabled(false);
        table.setEnabled(false);
        try {
            calculator.calculate();
        } catch (CalculationException ex) {
            table.setEnabled(true);
            ((JButton)e.getSource()).setEnabled(true);
            ExceptionsHandler.showErrorMessageDialog(ex);
        }
    }
}