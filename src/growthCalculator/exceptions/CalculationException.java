package growthCalculator.exceptions;

/**
 * GrowthCalculator
 * Created by Piotrek on 21-12-2015.
 *
 * Wyjątek informujący o nieprawidłowościach, które zaszły podczas obliczeń
 */
public class CalculationException extends Exception {
    public CalculationException(String s) {
        super(s);
    }
}
