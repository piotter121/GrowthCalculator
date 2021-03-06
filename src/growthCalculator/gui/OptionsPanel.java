package growthCalculator.gui;

import growthCalculator.calculator.GrowthCalculator;
import growthCalculator.calculator.growthCharts.factories.GrowthChartsFactory;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GrowthCalculator
 * Created by Piotrek on 04-01-2016.
 */
public class OptionsPanel extends JPanel implements ActionListener {
    public static final String BOY = "Chłopiec";
    public static final String GIRL = "Dziewczynka";
    public static final String HEIGHT = "Wzrost";
    public static final String WEIGHT = "Waga";

    private JRadioButton heightRadioButton = new JRadioButton(HEIGHT, true);
    private JRadioButton weightRadioButton = new JRadioButton(WEIGHT);
    private JRadioButton boyRadioButton = new JRadioButton(BOY, true);
    private JRadioButton girlRadioButton = new JRadioButton(GIRL);

    private ButtonGroup chooseSexGroup = new ButtonGroup();

    private GrowthCalculator calculator;

    public OptionsPanel(GrowthCalculator calculator) {
        super();

        this.calculator = calculator;

        JPanel chooseSexPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chooseSexGroup.add(boyRadioButton);
        chooseSexGroup.add(girlRadioButton);

        JPanel chooseMetricPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonGroup chooseMetricGroup = new ButtonGroup();
        chooseMetricGroup.add(heightRadioButton);
        chooseMetricGroup.add(weightRadioButton);

        boyRadioButton.setActionCommand(BOY);
        girlRadioButton.setActionCommand(GIRL);
        heightRadioButton.setActionCommand(HEIGHT);
        weightRadioButton.setActionCommand(WEIGHT);

        for (JRadioButton button: new JRadioButton[]{boyRadioButton, girlRadioButton, heightRadioButton, weightRadioButton})
            button.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(300, 150));

        chooseSexPanel.add(boyRadioButton);
        chooseSexPanel.add(girlRadioButton);
        chooseMetricPanel.add(heightRadioButton);
        chooseMetricPanel.add(weightRadioButton);

        add(new JLabel("Opcje"));
        add(chooseSexPanel);
        add(chooseMetricPanel);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            switch (((JRadioButton) e.getSource()).getActionCommand()) {
                case HEIGHT:
                    GrowthChartsFactory.setHeightFactory();
                    selectSex();
                    break;
                case WEIGHT:
                    GrowthChartsFactory.setWeightFactory();
                    selectSex();
                    break;
                case BOY:
                    calculator.setGrowthChart(GrowthChartsFactory.BoysGrowthChart());
                    break;
                case GIRL:
                    calculator.setGrowthChart(GrowthChartsFactory.GirlsGrowthChart());
                    break;
            }
        }
    }

    private void selectSex() {
        switch (chooseSexGroup.getSelection().getActionCommand()) {
            case BOY:
                calculator.setGrowthChart(GrowthChartsFactory.BoysGrowthChart());
                break;
            case GIRL:
                calculator.setGrowthChart(GrowthChartsFactory.GirlsGrowthChart());
                break;
        }
    }
}
