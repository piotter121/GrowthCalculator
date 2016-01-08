package growthCalculator.exceptions;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 08-01-2016.
 */
public class ExceptionsHandler {
    public static void showErrorMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Błąd", JOptionPane.ERROR_MESSAGE);
    }
}
