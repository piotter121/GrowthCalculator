package growthCalculator.gui;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.calculator.growthCharts.BoysWeightGrowthChart;
import growthCalculator.calculator.growthCharts.GirlsHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GirlsWeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-12-2015.
 */
public class DataTableModel extends AbstractTableModel implements Observer {
    private String[] colNames;
    private double[][] data;

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
    public boolean isCellEditable(int row, int col) {
        return col == 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        if ((o != null) && isCellEditable(row, col)) {
            data[row][col] = Double.parseDouble(o.toString());
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Calculator) {
            Calculator calculator = (Calculator) o;
            GrowthChart chart = calculator.getGrowthChart();
            if (chart instanceof BoysHeightGrowthChart || chart instanceof GirlsHeightGrowthChart)
                colNames[1] = "Wzrost";
            else if (chart instanceof BoysWeightGrowthChart || chart instanceof GirlsWeightGrowthChart)
                colNames[1] = "Waga";
            else
                colNames[1] = "";

            SortedMap<Integer, Double> calculatorData = new TreeMap<>(calculator.getUserData());
            calculatorData.putAll(calculator.getCalculationResult());

            // Obliczenie różnic
            SortedSet<Integer> agesInCalculator = new TreeSet<>(Integer::compareTo);
            agesInCalculator.addAll(calculator.getUserData().keySet());
            agesInCalculator.addAll(calculator.getCalculationResult().keySet());

            SortedSet<Integer> agesInTable = new TreeSet<>(Integer::compareTo);
            for (int i = 0; i < getRowCount(); i++)
                if (((double) getValueAt(i, 1)) != 0)
                    agesInTable.add(i + 1);

            SortedSet<Integer> diff = new TreeSet<>(agesInTable);
            diff.removeAll(agesInCalculator);

            agesInCalculator.stream().filter(age -> calculatorData.get(age) != ((double) getValueAt(age - 1, 1))).forEach(age -> setValueAt(calculatorData.get(age), age - 1, 1));

            for (Integer age: diff)
                setValueAt(0, age - 1, 1);
        }
    }

    public void deleteAllData() {
        for (int i = 0; i < getRowCount(); i++)
            setValueAt(0.0, i, 1);
    }
}