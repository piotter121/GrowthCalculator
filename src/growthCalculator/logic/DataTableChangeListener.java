package growthCalculator.logic;

import growthCalculator.calculator.Calculator;
import growthCalculator.gui.DataTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * GrowthCalculator
 * Created by Piotrek on 26-12-2015.
 */
public class DataTableChangeListener implements TableModelListener {

    private Calculator calculator;

    public DataTableChangeListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int column;
        if ((column = e.getColumn()) == 1) {
            DataTableModel model = (DataTableModel) e.getSource();
            int row = e.getFirstRow();
            int age = row + 1;

            Double valueFromTable = (Double) model.getValueAt(row, column);
            Double valueFromCalculator = calculator.getUserData().get(age);
            if (valueFromCalculator == null) {
                if (valueFromTable > 0)
                    calculator.set(age, valueFromTable);
            } else {
                if (valueFromTable > 0 && valueFromCalculator.compareTo(valueFromTable) != 0) {
                    calculator.set(age, valueFromTable);
                } else if (valueFromTable == 0) {
                    calculator.remove(age);
                }
            }
        }
    }
}
