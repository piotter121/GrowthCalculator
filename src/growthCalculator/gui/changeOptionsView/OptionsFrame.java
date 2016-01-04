package growthCalculator.gui.changeOptionsView;

import growthCalculator.calculator.Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * GrowthCalculator
 * Created by Piotrek on 03-12-2015.
 */
public class OptionsFrame extends JFrame implements Observer {
    private Calculator calculator;
    private JRadioButton boyRadioButton;
    private JRadioButton girlRadioButton;
    private JRadioButton heightRadioButton;
    private JRadioButton weightRadioButton;

    public OptionsFrame(Calculator calculator) {
        super("Opcje");
        setSize(new Dimension(300, 200));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Ustawienie panelu z całą zawartością
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Ustawienie panelu zawierającego przyciski do wyboru płci
        JPanel chooseSexPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        boyRadioButton = new JRadioButton("Chłopiec");
        girlRadioButton = new JRadioButton("Dziewczynka");
        ButtonGroup chooseSexGroup = new ButtonGroup();
        chooseSexGroup.add(boyRadioButton);
        chooseSexGroup.add(girlRadioButton);

        this.calculator = calculator;
        this.calculator.addObserver(this);

        setContentPane(contentPanel);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
