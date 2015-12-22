package growthCalculator.gui;

import growthCalculator.data.CalculatorData;
import growthCalculator.data.growthCharts.factories.HeightGrowthChartsFactory;
import growthCalculator.data.growthCharts.factories.WeightGrowthChartsFactory;
import growthCalculator.logic.Options;

import javax.swing.table.AbstractTableModel;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-12-2015.
 */
public class GrowthTableModel extends AbstractTableModel {
    private String[] colNames;
    private double[][] data;
    private int rows;

    public GrowthTableModel(int rows) {
        super();
        colNames = new String[2];
        colNames[0] = "Wiek";
        if (Options.getFactory() instanceof HeightGrowthChartsFactory) {
            colNames[1] = "Wzrost";
        } else if (Options.getFactory() instanceof WeightGrowthChartsFactory) {
            colNames[1] = "Waga";
        } else {
            colNames[1] = "";
        }
        this.rows = rows;
        fillUpWithZeros();
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public int getRowCount() {
        return rows;
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
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = Double.parseDouble(value.toString());
        fireTableCellUpdated(row, col);
    }

    public void fillUpWithZeros() {
        data = new double[rows][2];
        for (int i = 0; i < rows; i++) {
            setValueAt(0, i, 0);
            setValueAt(0, i, 1);
        }
    }
}