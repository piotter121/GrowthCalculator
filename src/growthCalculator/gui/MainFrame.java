package growthCalculator.gui;

import growthCalculator.calculator.GrowthCalculator;
import growthCalculator.logic.CalculateController;
import growthCalculator.logic.DataTableChangeListener;
import growthCalculator.logic.ReadDataFromFile;
import growthCalculator.logic.SaveDataToFile;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * GrowthCalculator
 * Created by Piotrek on 15-11-2015.
 */
public class MainFrame extends JFrame {

    private static final String FRAME_NAME = "Kalkulator rozwoju dziecka";
    private static final String CALCULATE_BUTTON_NAME = "Oblicz";
    private static final String OPEN_FILE_BUTTON_NAME = "Otwórz plik";
    private static final String SAVE_BUTTON_NAME = "Zapisz dane";
    private static final String CLEAR_DATA_BUTTON_NAME = "Wyczyść wszystko";

    public MainFrame () {
        super(FRAME_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1030,700);
        setLocationRelativeTo(null);

        /** Model */
        GrowthCalculator calculator = new GrowthCalculator();

        /** Panel przechowujący całą zawartość */
        JPanel contentPane = new JPanel();

        /** Panel przechowujący wykres siatki centylowej */
        GrowthChartPanel chartPanel = new GrowthChartPanel(calculator.getGrowthChart());

        /** Panel przechowujący prawą stronę okna */
        JPanel rightPanel = new JPanel();

        /** Panel przechowujący przyciski */
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        /** Przycisk rozpoczynający obliczanie */
        JButton calculateButton = new JButton(CALCULATE_BUTTON_NAME);

        /** Przycisk aktywujący zapisanie do pliku oszacowanych danych */
        JButton saveButton = new JButton(SAVE_BUTTON_NAME);

        /** Przycisk aktywujący otworzenie pliku w celu wczytania danych z niego */
        JButton openFileButton = new JButton(OPEN_FILE_BUTTON_NAME);

        /** Przycisk kasujący wszystkie dane */
        JButton clearDataButton = new JButton(CLEAR_DATA_BUTTON_NAME);

        /** Ustawienie tabeli danych */
        DataTableModel tableModel = new DataTableModel();
        tableModel.addTableModelListener(new DataTableChangeListener(calculator));
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        /** Ustawienie obserwatorów */
        calculator.addObserver(chartPanel);
        calculator.addObserver(tableModel);

        /** Ustawienie głównego kontenera */
        setContentPane(contentPane);

        /** Ustawienie layoutów głównych panelów */
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(300,700));

        scrollPane.setMinimumSize(new Dimension(300,50));
        scrollPane.setPreferredSize(new Dimension(300,183));
        scrollPane.setMaximumSize(new Dimension(300,311));

        buttonsPanel.setMinimumSize(new Dimension(300,50));
        buttonsPanel.setPreferredSize(new Dimension(300,183));
        buttonsPanel.setMaximumSize(new Dimension(300,200));

        /** Ustawienie ActionListenerów dla przycisków */
        openFileButton.addActionListener(new ReadDataFromFile(tableModel));
        calculateButton.addActionListener(e -> openFileButton.setEnabled(false));
        calculateButton.addActionListener(new CalculateController(calculator, table));
        saveButton.addActionListener(new SaveDataToFile(calculator.getCalculationResult()));
        clearDataButton.addActionListener(e -> {
            calculateButton.setEnabled(true);
            openFileButton.setEnabled(true);
            tableModel.deleteAllData();
            table.setEnabled(true);
        });

        /** Ułożenie przycisków */
        buttonsPanel.add(openFileButton);
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(clearDataButton);
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        contentPane.add(chartPanel);
        rightPanel.add(scrollPane);
        rightPanel.add(buttonsPanel);
        rightPanel.add(new OptionsPanel(calculator));
        contentPane.add(rightPanel);
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}