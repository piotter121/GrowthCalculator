package logic;

import data.CalculatedData;
import growthCharts.GrowthChart;
import data.UserData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 05-12-2015.
 */
public class ShowAllData {
    private JPanel chartContainer;
    private JTable resultTable;

    public ShowAllData(JPanel chartPanel, JTable resultTable) {
        this.chartContainer = chartPanel;
        this.resultTable = resultTable;
    }

    public JPanel show(GrowthChart growthChart, UserData userData, CalculatedData calculatedData) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        for (Integer centyl: growthChart.getCentylList()) {
            XYSeries series = new XYSeries(centyl);
            for (int age = 1; age <= 18; age++) {
                series.add(age, growthChart.getValueAt(age, centyl));
            }
            seriesCollection.addSeries(series);
        }
        if (userData.isSet()) {
            XYSeries series = new XYSeries("wprowadzone dane");
            for (int age: userData.getAge()) {
                series.add(age, userData.getData(age));
                resultTable.setValueAt(userData.getData(age), age-1, 1);
            }
            seriesCollection.addSeries(series);
        }
        JFreeChart chart = ChartFactory.createXYLineChart("Wykres",
                "wiek", "wartość", seriesCollection, PlotOrientation.VERTICAL, true, true,
                false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700,700));
        chartContainer.removeAll();
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.validate();

        return chartPanel;
    }
}
