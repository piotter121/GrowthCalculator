package growthCalculator.logic;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;
import java.util.SortedMap;

import static javax.swing.JFileChooser.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * GrowthCalculator
 * Created by Piotrek on 26-12-2015.
 */
public class SaveDataToFile implements ActionListener {
    private SortedMap<Integer, Double> dataToSave;

    public SaveDataToFile(SortedMap<Integer, Double> data) {
        dataToSave = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dataToSave.isEmpty()) {
            JFileChooser chooser = new JFileChooser();

            chooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            chooser.setDialogTitle("Zapisz do pliku");
            chooser.setApproveButtonText("Zapisz");
            int returnVal = chooser.showSaveDialog(null);
            switch (returnVal) {
                case APPROVE_OPTION:
                    saveToFile(chooser.getSelectedFile());
                    break;
                case ERROR_OPTION:
                    JOptionPane.showMessageDialog(null, "Nastąpił nieoczekiwany błąd zapisu", "Błąd",
                            ERROR_MESSAGE);
                    break;
            }
        }
    }

    private void saveToFile(File file) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"));
            for (Map.Entry<Integer, Double> entry: dataToSave.entrySet()) {
                String line = entry.getKey() + " " + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Błąd zapisu do pliku", ERROR_MESSAGE);
        }
    }
}
