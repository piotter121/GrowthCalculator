package growthCalculator.gui;

import growthCalculator.calculator.Calculator;
import growthCalculator.calculator.growthCharts.factories.GrowthChartsFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GrowthCalculator
 * Created by Piotrek on 04-01-2016.
 */
public class OptionsPanel extends JPanel {
    public static final String BOY = "Chłopiec";
    public static final String GIRL = "Dziewczynka";
    public static final String HEIGHT = "Wzrost";
    public static final String WEIGHT = "Waga";

    private JRadioButton heightRadioButton = new JRadioButton(HEIGHT, true);
    private JRadioButton weightRadioButton = new JRadioButton(WEIGHT);
    private JRadioButton boyRadioButton = new JRadioButton(BOY, true);
    private JRadioButton girlRadioButton = new JRadioButton(GIRL);

    public OptionsPanel(Calculator calculator) {
        super();

        JLabel label = new JLabel("Opcje");

        JPanel chooseSexPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonGroup chooseSexGroup = new ButtonGroup();
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

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JRadioButton) {
                    String command = ((JRadioButton) e.getSource()).getActionCommand();
                    switch (command) {
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
                String command = chooseSexGroup.getSelection().getActionCommand();
                switch (command) {
                    case BOY:
                        calculator.setGrowthChart(GrowthChartsFactory.BoysGrowthChart());
                        break;
                    case GIRL:
                        calculator.setGrowthChart(GrowthChartsFactory.GirlsGrowthChart());
                        break;
                }
            }
        };

        for (JRadioButton button: new JRadioButton[]{boyRadioButton, girlRadioButton, heightRadioButton, weightRadioButton})
            button.addActionListener(listener);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(300, 150));

        chooseSexPanel.add(boyRadioButton);
        chooseSexPanel.add(girlRadioButton);
        chooseMetricPanel.add(heightRadioButton);
        chooseMetricPanel.add(weightRadioButton);

        add(label);
        add(chooseSexPanel);
        add(chooseMetricPanel);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
