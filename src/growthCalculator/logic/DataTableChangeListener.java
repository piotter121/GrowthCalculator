package growthCalculator.logic;

import growthCalculator.calculator.GrowthCalculator;
import growthCalculator.exceptions.DecreasingDataOrderException;
import growthCalculator.exceptions.ExceptionsHandler;
import growthCalculator.gui.DataTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * GrowthCalculator
 * Created by Piotrek on 26-12-2015.
 */
public class DataTableChangeListener implements TableModelListener {

    private GrowthCalculator calculator;
    private TableModel model;
    private int row;
    private int column;

    public DataTableChangeListener(GrowthCalculator calculator) {
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
        } catch (IllegalArgumentException | DecreasingDataOrderException e1) {
            ExceptionsHandler.showErrorMessageDialog(e1);
            model.setValueAt(0, row, column);
        }
    }
}
