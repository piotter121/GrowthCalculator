package logic;

import data.CalculatedData;
import data.UserData;
import growthCharts.GrowthChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
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
        JFreeChart chart = ChartFactory.createXYLineChart("Wykres", "wiek", "wartość",
                createSeriesCollection(growthChart, userData, calculatedData),
                PlotOrientation.VERTICAL, true, true, false);

        ((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
        chart.getPlot().setBackgroundPaint( new Color(255,255,255,0) );
       // chart.getPlot().setBackgroundImageAlpha(0.0f);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700,700));

        chartContainer.removeAll();
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.validate();

        return chartPanel;
    }

    private XYSeriesCollection createSeriesCollection(GrowthChart growthChart, UserData uData, CalculatedData cData) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        for (Integer centyl: growthChart.getCentylList()) {
            XYSeries series = new XYSeries(centyl + " centyl");
            for (int age = 1; age <= 18; age++) {
                series.add(age, growthChart.getValueAt(age, centyl));
            }
            seriesCollection.addSeries(series);
        }
        if (uData.isSet()) {
            XYSeries series = new XYSeries("wprowadzone dane");
            for (int age: uData.getAges()) {
                series.add(age, uData.getData(age));
                resultTable.setValueAt(uData.getData(age), age-1, 1);
            }
            seriesCollection.addSeries(series);
        }

        return seriesCollection;
    }
}
