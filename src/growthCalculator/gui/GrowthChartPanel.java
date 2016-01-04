package growthCalculator.gui;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.GrowthChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-12-2015.
 */
public class GrowthChartPanel extends JPanel implements Observer {

    public GrowthChartPanel(GrowthChart chart) {
        super(new BorderLayout());
        refresh(chart, null);
    }

    private void refresh(GrowthChart chart, Map<Integer, Double> data) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        for (Integer percentile: chart.getPercentilesList()) {
            XYSeries series = new XYSeries(percentile + " centyl");
            for (int age = 1; age <= 18; age++)
                series.add(age, chart.getValueAt(age, percentile));
            seriesCollection.addSeries(series);
        }

        if (data != null && !data.isEmpty())
            seriesCollection.addSeries(createSeries(data));

        JFreeChart freeChart = ChartFactory.createXYLineChart("Wykres", "wiek", "wartość", seriesCollection,
                PlotOrientation.VERTICAL, true, true, false);

        ((NumberAxis)freeChart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
        freeChart.getPlot().setBackgroundPaint(new Color(114, 115, 0, 127));

        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setPreferredSize(new Dimension(700,700));

        removeAll();
        add(chartPanel, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        Calculator calculator;
        try {
            calculator = (Calculator) o;
        } catch (ClassCastException e) {
            return;
        }

        if (calculator.hasData()) {
            TreeMap<Integer, Double> data = new TreeMap<>(calculator.getUserData());
            data.putAll(new TreeMap<>(calculator.getCalculationResult()));
            refresh(calculator.getGrowthChart(), data);
        } else
            refresh(calculator.getGrowthChart(), null);
    }

    private XYSeries createSeries(Map<Integer, Double> data) {
        XYSeries series = new XYSeries("dane dziecka");
        Integer[] ages = (data.keySet()).toArray(new Integer[data.keySet().size()]);
        for (Integer age: ages)
            series.add(age, data.get(age));
        return series;
    }
}
