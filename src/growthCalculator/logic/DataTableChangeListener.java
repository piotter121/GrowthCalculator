package growthCalculator.logic;

import growthCalculator.calculator.Calculator;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import static javax.swing.event.TableModelEvent.ALL_COLUMNS;

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
        if (e.getColumn() == ALL_COLUMNS)
            return;
        TableModel model = (TableModel) e.getSource();
        int row = e.getFirstRow();
        int column = e.getColumn();
        Double valueFromTable = (Double) model.getValueAt(row, column);
        try {
            calculator.set(row + 1, valueFromTable);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
