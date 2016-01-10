package growthCalculator.logic;

import growthCalculator.calculator.Calculator;
import growthCalculator.exceptions.ExceptionsHandler;
import growthCalculator.exceptions.NonGrowingDataOrderException;
import growthCalculator.gui.DataTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * GrowthCalculator
 * Created by Piotrek on 26-12-2015.
 */
public class DataTableChangeListener implements TableModelListener {

    private Calculator calculator;
    private TableModel model;
    private int row;
    private int column;

    public DataTableChangeListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if ((column = e.getColumn()) == 1) {
            model = (DataTableModel) e.getSource();
            row = e.getFirstRow();
            int age = row + 1;

            Double valueFromTable = (Double) model.getValueAt(row, column);
            Double valueFromCalculator = calculator.getUserData().get(age);
            if (valueFromCalculator == null) {
                if (valueFromTable > 0) {
                    setValueToCalculator(age, valueFromTable);
                }
            } else {
                if (valueFromTable > 0 && valueFromCalculator.compareTo(valueFromTable) != 0) {
                    setValueToCalculator(age, valueFromTable);
                } else if (valueFromTable == 0) {
                    calculator.remove(age);
                }
            }
        }
    }

    private void setValueToCalculator(int age, double val) {
        try {
            calculator.set(age, val);
        } catch (IllegalArgumentException | NonGrowingDataOrderException e1) {
            ExceptionsHandler.showErrorMessageDialog(e1);
            model.setValueAt(0, row, column);
        }
    }
}
