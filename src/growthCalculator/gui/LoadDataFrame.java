package growthCalculator.gui;

import growthCalculator.logic.LoadMeasuredFeatures;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * GrowthCalculator
 * Created by Piotrek on 01-12-2015.
 */
public class LoadDataFrame extends JFrame {
    private LoadMeasuredFeatures loadMeasuredFeatures;

    private JPanel contentPanel;
    private JPanel buttonsPanel;
    private JButton okButton;
    private JButton openFileButton;
    private JButton cancelButton;
    private JButton clearButton;
    private JTable dataTable;
    private GrowthTableModel tableModel;

    public LoadDataFrame(LoadMeasuredFeatures controller) {
        super("Wczytaj dane");
        setSize(new Dimension(300,500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        loadMeasuredFeatures = controller;
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        buttonsPanel = new JPanel(new FlowLayout());
        okButton = new JButton("OK");
        openFileButton = new JButton("Otwórz plik");
        cancelButton = new JButton("Anuluj");
        clearButton = new JButton("Wyczyść tabelkę");
        JScrollPane tablePane = createDataTable();

        // ActionListener dla przycisku Anuluj
        cancelButton.addActionListener(e -> dispose());

        // ActionListener dla przycisku OK
        okButton.addActionListener(e -> {
            dispose();
            process();
        });

        // ActionListener dla przycisku Wyczyść tabelkę
        clearButton.addActionListener(e -> purgeTable());

        // ActionListener dla przycisku Otwórz plik
        openFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory())
                        return true;

                    String ext = null;
                    String s = f.getName();
                    int i = s.lastIndexOf('.');

                    if (i > 0 &&  i < s.length() - 1) {
                        ext = s.substring(i+1).toLowerCase();
                    }

                    return ext != null && ext.equals("txt");
                }

                @Override
                public String getDescription() {
                    return "Pliki tekstowe";
                }
            });
            int returnedValue = fileChooser.showOpenDialog(LoadDataFrame.this);
            switch (returnedValue) {
                case JFileChooser.CANCEL_OPTION:
                    break;
                case JFileChooser.APPROVE_OPTION:
                    LoadDataFrame.this.dispose();
                    loadMeasuredFeatures.loadData(fileChooser.getSelectedFile());
                    loadMeasuredFeatures.sendToCalculator();
                    break;
                case JFileChooser.ERROR_OPTION:
                    JOptionPane.showMessageDialog(fileChooser, "Nastąpił niespodziewany błąd", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

        contentPanel.add(tablePane);
        buttonsPanel.add(openFileButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(clearButton);
        contentPanel.add(buttonsPanel);
        contentPanel.setMaximumSize(new Dimension(300,600));
        setContentPane(contentPanel);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JScrollPane createDataTable() {
        tableModel = new GrowthTableModel(5) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return true;
            }
        };
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(300,200));
        return scrollPane;
    }

    public void purgeTable() {
        tableModel.fillUpWithZeros();
    }

    private void process() {
        loadMeasuredFeatures.loadData(tableModel);
        loadMeasuredFeatures.sendToCalculator();
    }
}
