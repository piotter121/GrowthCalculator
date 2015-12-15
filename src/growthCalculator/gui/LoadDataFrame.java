package growthCalculator.gui;

import growthCalculator.logic.LoadData;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * GrowthCalculator
 * Created by Piotrek on 01-12-2015.
 */
public class LoadDataFrame extends JFrame {
    private LoadData loadData;

    private JPanel contentPanel;
    private JPanel buttonsPanel;
    private JButton okButton;
    private JButton openFileButton;
    private JButton cancelButton;
    private JButton clearButton;
    private JTable dataTable;
    private GrowthTableModel tableModel;

    public LoadDataFrame(LoadData controller) {
        super("Wczytaj dane");
        setSize(new Dimension(300,500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        loadData = controller;
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        buttonsPanel = new JPanel(new FlowLayout());
        okButton = new JButton("OK");
        openFileButton = new JButton("Otwórz plik");
        cancelButton = new JButton("Anuluj");
        clearButton = new JButton("Wyczyść tabelkę");
        JScrollPane tablePane = createDataTable();

        cancelButton.addActionListener(e -> dispose());
        okButton.addActionListener(e -> {
            dispose();
            loadData.loadData(tableModel.getData());
            loadData.sendToCalculator();
        });
        clearButton.addActionListener(e -> tableModel.fillUpWithZeros());
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
                    loadData.loadData(fileChooser.getSelectedFile());
                    loadData.sendToCalculator();
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
}
