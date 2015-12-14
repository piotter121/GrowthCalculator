package growthCalculator.logic;

import growthCalculator.data.CalculatorData;
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

    public void show(GrowthChart growthChart, CalculatorData userData, CalculatorData calculatedData) {
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

    private XYSeriesCollection
    createSeriesCollection(GrowthChart growthChart, CalculatorData userData, CalculatorData calculatedData) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        for (Integer centyl: growthChart.getPercentilesList()) {
            XYSeries series = new XYSeries(centyl + " centyl");
            for (int age = 1; age <= 18; age++) {
                series.add(age, growthChart.getValueAt(age, centyl));
            }
            seriesCollection.addSeries(series);
        }

        if (userData.isSet()) {
            seriesCollection.addSeries(createSeries(userData, "wprowadzone dane"));
        }

        if (calculatedData.isSet()) {
            seriesCollection.addSeries(createSeries(calculatedData, "obliczone dane"));
        }

        return seriesCollection;
    }

    private XYSeries createSeries(CalculatorData data, String name) {
        XYSeries series = new XYSeries(name);
        for (int age: data.getAges()) {
            series.add(age, data.getValue(age));
            resultTable.setValueAt(data.getValue(age), age-1, 1);
        }
        return series;
    }
}
