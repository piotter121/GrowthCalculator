package growthCalculator.gui;

import growthCalculator.data.CalculatorData;
import growthCalculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.growthCharts.factories.HeightGrowthChartsFactory;
import growthCalculator.growthCharts.factories.WeightGrowthChartsFactory;
import growthCalculator.logic.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
    private CalculateData calculateDataController;
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
        resultsTable = new JTable(new GrowthTableModel(18));
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        showAllDataController = new ShowAllData(chartPanel, resultsTable);
        calculateDataController = new CalculateData(showAllDataController);
        loadDataController = new LoadData(calculateDataController);
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

class GrowthTableModel extends AbstractTableModel {
    private String[] colNames;
    private double[][] data;
    private int rows;

    GrowthTableModel(int rows) {
        super();
        colNames = new String[2];
        colNames[0] = "Wiek";
        if (Options.getFactory() instanceof HeightGrowthChartsFactory) {
            colNames[1] = "Wzrost";
        } else if (Options.getFactory() instanceof WeightGrowthChartsFactory) {
            colNames[1] = "Waga";
        } else {
            colNames[1] = "";
        }
        this.rows = rows;
        data = new double[this.rows][2];
        for (int i = 0; i < this.rows; i++)
            data[i][0] = data[i][1] = 0;
    }

    public CalculatorData getData() {
        CalculatorData cd = new CalculatorData();
        for (int i = 0; i < rows; i++)
            if (data[i][0] > 0 && data[i][0] < 19 && data[i][1] > 0)
                cd.add((int) Math.round(data[i][0]), data[i][1]);
        return cd;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = Double.parseDouble(value.toString());
        fireTableCellUpdated(row, col);
    }
}