package gui;

import controllers.LoadData;

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

    public LoadDataFrame(LoadData controller) {
        super("Wczytaj dane");
        setSize(new Dimension(400,600));
        this.loadData = controller;
        contentPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        okButton = new JButton("OK");
        openFileButton = new JButton("Otwórz plik");
        cancelButton = new JButton("Anuluj");
        createDataTable();

        cancelButton.addActionListener(e -> dispose());

        buttonsPanel.add(openFileButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        contentPanel.add(buttonsPanel);
        setContentPane(contentPanel);
    }

    private void createDataTable() {

    }
}
