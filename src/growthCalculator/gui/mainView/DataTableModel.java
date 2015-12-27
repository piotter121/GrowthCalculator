package growthCalculator.gui.mainView;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.*;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-12-2015.
 */
public class DataTableModel extends AbstractTableModel implements Observer {
    protected String[] colNames;
    protected double[][] data;

    public DataTableModel() {
        super();
        colNames = new String[2];
        colNames[0] = "Wiek";
        colNames[1] = "Wartości";
        data = new double[18][2];
        for (int i = 0; i < 18; i++) {
            data[i][0] = i + 1;
            fireTableCellUpdated(i, 0);
            setValueAt(0, i, 1);
        }
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public int getRowCount() {
        return 18;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        if ((o != null) && col == 1) {
            data[row][col] = Double.parseDouble(o.toString());
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Calculator calculator;
        try {
            calculator = (Calculator) o;
        } catch (ClassCastException e) {
            return;
        }
        GrowthChart chart = calculator.getGrowthChart();
        if (chart instanceof BoysHeightGrowthChart || chart instanceof GirlsHeightGrowthChart)
            colNames[1] = "Wzrost";
        else if (chart instanceof BoysWeightGrowthChart || chart instanceof GirlsWeightGrowthChart)
            colNames[1] = "Waga";

        for (Integer i: calculator.getUserData().keySet())
            setValueAt(calculator.getUserData().get(i), i - 1, 1);
        for (Integer i: calculator.getCalculationResult().keySet())
            setValueAt(calculator.getCalculationResult().get(i), i - 1, 1);

        // Opróżnienie pozostałych wierszy
        Set<Integer> emptyFields = new TreeSet<>(Integer::compareTo);
        for (int i = 1; i <= 18; i++)
            emptyFields.add(i);
        Set<Integer> dataFields = new TreeSet<>(calculator.getUserData().keySet());
        dataFields.addAll(calculator.getCalculationResult().keySet());
        emptyFields.removeAll(dataFields);
        for (Integer i: emptyFields)
            setValueAt(0, i - 1, 1);
    }
}