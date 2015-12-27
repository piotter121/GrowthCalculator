package growthCalculator.gui.mainView;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.gui.loadDataView.LoadDataFrame;
import growthCalculator.logic.SaveDataToFile;

import javax.swing.*;
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

    // View
    private JPanel contentPane;
    private GrowthChartPanel chartPanel;
    private JPanel rightPanel;
    private JPanel buttonsPanel;
    private JButton loadButton;
    private JButton saveButton;
    private JButton optionsButton;
    private DataTableModel tableModel;

    // Model
    private Calculator calculator;

    public MainFrame () {
        super(FRAME_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1030,700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        chartPanel = new GrowthChartPanel(new BoysHeightGrowthChart());
        rightPanel = new JPanel();
        buttonsPanel = new JPanel(new FlowLayout());
        loadButton = new JButton(LOAD_BUTTON_NAME);
        saveButton = new JButton(SAVE_BUTTON_NAME);
        optionsButton = new JButton(OPTIONS_BUTTON_NAME);

        tableModel = new DataTableModel();
        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        calculator = new Calculator();

        // Ustawienie głównego kontenera
        setContentPane(contentPane);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        rightPanel.setPreferredSize(new Dimension(300,700));
        scrollPane.setMinimumSize(new Dimension(300,50));
        scrollPane.setPreferredSize(new Dimension(300,183));
        scrollPane.setMaximumSize(new Dimension(300,311));

        // Ustawienie ActionListenerów dla przycisków
        loadButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new LoadDataFrame(calculator).setVisible(true)));
        saveButton.addActionListener(new SaveDataToFile(calculator));

        buttonsPanel.setMinimumSize(new Dimension(300,50));
        buttonsPanel.setPreferredSize(new Dimension(300,183));
        buttonsPanel.setMaximumSize(new Dimension(300,200));
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(optionsButton);

        contentPane.add(chartPanel);
        rightPanel.add(scrollPane);
        rightPanel.add(buttonsPanel);
        contentPane.add(rightPanel);

        calculator.addObserver(chartPanel);
        calculator.addObserver(tableModel);
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}