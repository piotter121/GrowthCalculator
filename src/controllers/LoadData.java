package controllers;

import gui.LoadDataFrame;
import org.jfree.chart.ChartPanel;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 23-11-2015.
 */
public class LoadData{
    private ChartPanel chartPanel;
    private JTable resultsTable;
    private JFrame loadDataFrame;

    public LoadData(ChartPanel chartPanel, JTable resultsTable) {
        this.chartPanel = chartPanel;
        this.resultsTable = resultsTable;
        loadDataFrame = new LoadDataFrame(this);

    }

    public void start() {
        SwingUtilities.invokeLater(() -> loadDataFrame.setVisible(true));

    }
}
