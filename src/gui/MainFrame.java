package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public class MainFrame extends JFrame {

    private static final String FRAME_NAME = "Kalkulator rozwoju dziecka";
    private static final String LOAD_BUTTON_NAME = "Wczytaj dane";
    private static final String SAVE_BUTTON_NAME = "Zapisz dane";
    private static final String OPTIONS_BUTTON_NAME = "Opcje obliczeń";

    private JPanel contentPane;
    private ChartPanel chartPanel;
    private JPanel rightPanel;
    private JTable resultsTable;
    private JPanel buttonsPanel;
    private JButton loadButton;
    private JButton saveButton;
    private JButton optionsButton;

    public MainFrame () {
        super(FRAME_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1030,700);

        contentPane = new JPanel();
        createChartPanel();
        rightPanel = new JPanel();
        buttonsPanel = new JPanel(new FlowLayout());
        loadButton = new JButton(LOAD_BUTTON_NAME);
        saveButton = new JButton(SAVE_BUTTON_NAME);
        optionsButton = new JButton(OPTIONS_BUTTON_NAME);

        // Ustawienie głównego kontenera
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(300,700));

        createTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setPreferredSize(new Dimension(300,100));

        configureButtons();

        contentPane.add(chartPanel);
        rightPanel.add(scrollPane);
        rightPanel.add(buttonsPanel);
        contentPane.add(rightPanel);
    }

    private void createChartPanel() {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };
        ds.addSeries("series1", data);
        JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
                "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700,700));
        chartPanel.setBackground(Color.cyan);
    }

    private void configureButtons() {
        // TO DO dodać action listenery


        buttonsPanel.setPreferredSize(new Dimension(300,200));
        buttonsPanel.setBackground(Color.yellow);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(optionsButton);
    }

    private void createTable() {
        TableModel dataModel = new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Wiek";
                    case 1:
                        return "Wzrost";
                }
                return "";
            }
            public int getColumnCount() {
                return 2;
            }
            public int getRowCount() {
                return 10;
            }
            public Object getValueAt(int row, int col) {
                return 0;
            }
        };
        resultsTable = new JTable(dataModel);
    }

    public static void main(String[] args) {
        final MainFrame frame;
        frame = new MainFrame();
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}
