package growthCalculator.logic;

import growthCalculator.data.CalculatedData;
import growthCalculator.data.UserData;
import growthCalculator.growthCharts.GrowthChart;
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

    public void show(GrowthChart growthChart, UserData userData, CalculatedData calculatedData) {
        JFreeChart chart = ChartFactory.createXYLineChart("Wykres", "wiek", "wartość",
                createSeriesCollection(growthChart, userData, calculatedData),
                PlotOrientation.VERTICAL, true, true, false);

        ((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
        chart.getPlot().setBackgroundPaint(new Color(114, 115, 0, 127));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700,700));

        chartContainer.removeAll();
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.validate();
    }

    private XYSeriesCollection createSeriesCollection(GrowthChart growthChart, UserData uData, CalculatedData cData) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        for (Integer centyl: growthChart.getPercentilesList()) {
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

        if (cData.isSet()) {
            XYSeries series = new XYSeries("obliczone dane");
            for (int age: cData.getAges()) {
                series.add(age, cData.getValue(age));
                resultTable.setValueAt(cData.getValue(age), age-1, 1);
            }
            seriesCollection.addSeries(series);
        }

        return seriesCollection;
    }
}
