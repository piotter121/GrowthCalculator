package growthCalculator.exceptions;

import javax.swing.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 08-01-2016.
 */
public class ExceptionsHandler {
    public static void showErrorMessageDialog(Exception exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
    }
}
