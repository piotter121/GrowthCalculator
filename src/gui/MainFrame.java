package gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Piotrek on 15-11-2015.
 */
public class MainFrame extends JFrame {

    private JPanel contentPane = new JPanel();
    private JPanel chartPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JTable resultsTable;
    private JPanel buttonsPanel = new JPanel(new FlowLayout());
    private JButton loadButton = new JButton("Wczytaj dane");
    private JButton saveButton = new JButton("Zapisz dane");
    private JButton optionsButton = new JButton("Opcje obliczeń");

    public MainFrame () {
        super("Kalkulator rozwoju dziecka");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);

        // Ustawienie głównego kontenera
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout());

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        createTable();
        JScrollPane scrollpane = new JScrollPane(resultsTable);

        configureButtons();

        contentPane.add(chartPanel);
        rightPanel.add(scrollpane);
        rightPanel.add(buttonsPanel);
        contentPane.add(rightPanel);
    }

    private void configureButtons() {
        // TO DO dodać action listenery

        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(optionsButton);
    }

    private void createTable() {
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 2; }
            public int getRowCount() { return 10;}
            public Object getValueAt(int row, int col) { return 0; }
        };
        resultsTable = new JTable(dataModel);
    }

    public static void main(String[] args) {
        final MainFrame frame;
        frame = new MainFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
