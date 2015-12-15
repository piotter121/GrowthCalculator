package growthCalculator.gui;

import growthCalculator.data.CalculatorData;
import growthCalculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.logic.*;

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

    private LoadData loadDataController;
    private SaveData saveDataController;
    private ChangeOptions changeOptionsController;
    private ShowAllData showAllDataController;

    private JPanel contentPane;
    private JPanel chartPanel;
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
        chartPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel();
        buttonsPanel = new JPanel(new FlowLayout());
        loadButton = new JButton(LOAD_BUTTON_NAME);
        saveButton = new JButton(SAVE_BUTTON_NAME);
        optionsButton = new JButton(OPTIONS_BUTTON_NAME);

        GrowthTableModel tableModel = new GrowthTableModel(18);
        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        showAllDataController = new ShowAllData(chartPanel, tableModel);
        loadDataController = new LoadData(new CalculateData(showAllDataController));
        saveDataController = new SaveData();
        changeOptionsController = new ChangeOptions();

        showAllDataController.show(new BoysHeightGrowthChart(), new CalculatorData(), new CalculatorData());

        // Ustawienie głównego kontenera
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(300,700));

        scrollPane.setMinimumSize(new Dimension(300,50));
        scrollPane.setPreferredSize(new Dimension(300,183));
        scrollPane.setMaximumSize(new Dimension(300,311));

        configureButtons();

        contentPane.add(chartPanel);
        rightPanel.add(scrollPane);
        rightPanel.add(buttonsPanel);
        contentPane.add(rightPanel);
        setLocationRelativeTo(null);
    }

    private void configureButtons() {
        loadButton.addActionListener(e -> loadDataController.start());

        buttonsPanel.setMinimumSize(new Dimension(300,50));
        buttonsPanel.setPreferredSize(new Dimension(300,183));
        buttonsPanel.setMaximumSize(new Dimension(300,200));
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(optionsButton);
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}