package growthCalculator.gui.loadDataView;

import growthCalculator.calculator.Calculator;
import growthCalculator.exceptions.CalculationException;
import growthCalculator.gui.mainView.DataTableModel;
import growthCalculator.logic.DataTableChangeListener;
import growthCalculator.logic.ReadDataFromFile;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * GrowthCalculator
 * Created by Piotrek on 01-12-2015.
 */
public class LoadDataFrame extends JFrame {

    private JButton okButton;
    private JButton openFileButton;
    private JButton clearButton;
    private LoadDataTableModel tableModel;

    public class LoadDataTableModel extends DataTableModel {
        private LoadDataTableModel(Map<Integer, Double> data) {
            super();
            for (Integer i: data.keySet())
                setValueAt(data.get(i), i - 1, 1);
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 1;
        }

        public void deleteAllData() {
            for (int i = 0; i < getRowCount(); i++)
                setValueAt(0.0, i, 1);
        }
    }

    public LoadDataFrame(Calculator calculator) {
        super("Wczytaj dane");
        setSize(new Dimension(300,500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel();

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        okButton = new JButton("OK");
        openFileButton = new JButton("Otwórz plik");
        clearButton = new JButton("Wyczyść tabelkę");
        tableModel = new LoadDataTableModel(calculator.getUserData());
        JTable dataTable = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(dataTable);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        tablePane.setPreferredSize(new Dimension(300,200));

        // TableModelListener dla tabelki
        tableModel.addTableModelListener(new DataTableChangeListener(calculator));

        // ActionListener dla przycisku OK
        okButton.addActionListener(e -> {
            dispose();
            try {
                calculator.calculate();
            } catch (CalculationException e1) {
                JOptionPane.showMessageDialog(LoadDataFrame.this, e1.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener dla przycisku Wyczyść tabelkę
        clearButton.addActionListener(e -> tableModel.deleteAllData());

        // ActionListener dla przycisku Otwórz plik
        openFileButton.addActionListener(new ReadDataFromFile(tableModel));

        contentPanel.add(tablePane);
        buttonsPanel.add(openFileButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(clearButton);
        contentPanel.add(buttonsPanel);
        contentPanel.setMaximumSize(new Dimension(300,500));
        setContentPane(contentPanel);
    }

}
