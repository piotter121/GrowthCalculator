package growthCalculator.exceptions;

import javax.swing.JOptionPane;

/**
 * GrowthCalculator
 * Created by Piotrek on 08-01-2016.
 *
 * Klasa do obsługi wyjątków w programie
 */
public class ExceptionsHandler {
    public static void showErrorMessageDialog(Exception exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
    }
}
