package growthCalculator.logic;

import growthCalculator.calculator.Calculator;
import growthCalculator.gui.loadDataView.LoadDataFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * GrowthCalculator
 * Created by Piotrek on 27-12-2015.
 */
public class ReadDataFromFile implements ActionListener {
    private LoadDataFrame.LoadDataTableModel table;

    public ReadDataFromFile(LoadDataFrame.LoadDataTableModel table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe", "txt");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Otwórz plik");
        chooser.setApproveButtonText("Otwórz");
        int returnVal = chooser.showOpenDialog(null);
        switch (returnVal) {
            case JFileChooser.APPROVE_OPTION:
                loadDataFromFile(chooser.getSelectedFile());
                break;
            case JFileChooser.ERROR_OPTION:
                JOptionPane.showMessageDialog(chooser, "Nastąpił nieoczekiwany błąd", "Błąd", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void loadDataFromFile(File file) {
        if (file.canRead()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splitedLine = line.split("\\s+");
                    if (splitedLine.length >= 2) {
                        int age = Integer.parseInt(splitedLine[0]);
                        double value = Double.parseDouble(splitedLine[1]);
                        table.setValueAt(value, age - 1, 1);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Błąd odczytu pliku", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
