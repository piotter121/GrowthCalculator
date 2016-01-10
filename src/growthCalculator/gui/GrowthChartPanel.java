package growthCalculator.gui;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.GrowthChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

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

    private void refresh(GrowthChart chart, SortedMap<Integer, Double> data) {
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

        XYPlot plot = freeChart.getXYPlot();
        ((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setBaseShapesVisible(false);
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesPaint(2, Color.CYAN);
        renderer.setSeriesPaint(3, Color.green);
        renderer.setSeriesPaint(4, Color.CYAN);
        renderer.setSeriesPaint(5, Color.blue);
        renderer.setSeriesPaint(6, Color.red);
        if (data != null && !data.isEmpty()) {
            Shape cross = ShapeUtilities.createRegularCross(3, 1);
            renderer.setSeriesShapesVisible(7, true);
            renderer.setSeriesShape(7, cross);
            renderer.setSeriesPaint(7, Color.BLACK);
        }
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(freeChart);

        removeAll();
        add(chartPanel, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Calculator) {
            Calculator calculator = (Calculator) o;
            if (calculator.hasData()) {
                SortedMap<Integer, Double> data = new TreeMap<>(calculator.getUserData());
                data.putAll(new TreeMap<>(calculator.getCalculationResult()));
                refresh(calculator.getGrowthChart(), data);
            } else
                refresh(calculator.getGrowthChart(), null);
        }
    }

    private XYSeries createSeries(SortedMap<Integer, Double> data) {
        XYSeries series = new XYSeries("dane dziecka");
        for (Map.Entry<Integer, Double> entry: data.entrySet())
            series.add(entry.getKey(), entry.getValue());
        return series;
    }
}
