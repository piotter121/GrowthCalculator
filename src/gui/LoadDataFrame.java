package gui;

import logic.LoadData;

import javax.swing.*;
import java.awt.*;

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
    private JTable dataTable;
    private GrowthTableModel tableModel;

    public LoadDataFrame(LoadData controller) {
        super("Wczytaj dane");
        setSize(new Dimension(300,500));
        this.loadData = controller;
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
        buttonsPanel = new JPanel(new FlowLayout());
        okButton = new JButton("OK");
        openFileButton = new JButton("Otwórz plik");
        cancelButton = new JButton("Anuluj");
        createDataTable();

        cancelButton.addActionListener(e -> dispose());
        okButton.addActionListener(e -> {
            loadData.loadData(tableModel.getData());
            dispose();
        });

        buttonsPanel.add(openFileButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        contentPanel.add(buttonsPanel);
        contentPanel.setMaximumSize(new Dimension(300,600));
        setContentPane(contentPanel);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void createDataTable() {
        tableModel = new GrowthTableModel(){
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 1;
            }
        };
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(300,200));
        contentPanel.add(scrollPane);
    }
}
