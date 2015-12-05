package controllers;

import data.CalculatedData;
import growthCharts.GrowthChart;
import data.UserData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 05-12-2015.
 */
public class ShowAllData {
    private ChartPanel chartPanel;
    private JTable resultTable;

    public ShowAllData(ChartPanel chartPanel, JTable resultTable) {
        this.chartPanel = chartPanel;
        this.resultTable = resultTable;
    }

    public ChartPanel show(GrowthChart growthChart, UserData userData, CalculatedData calculatedData) {
        DefaultXYDataset dataSet = new DefaultXYDataset();
        for (Integer centyl: growthChart.getCentylList()) {
            double[][] data = new double[2][18];
            for (int i = 0; i < 18; i++) {
                data[0][i] = i+1;
                data[1][i] = growthChart.getValueAt(i+1,centyl);
            }
            dataSet.addSeries(centyl,data);
        }
        JFreeChart chart = ChartFactory.createXYLineChart("Wykres",
                "wiek", "wartość", dataSet, PlotOrientation.VERTICAL, true, true,
                false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700,700));
        return chartPanel;
    }
}
