package growthCalculator.exceptions;

/**
 * GrowthCalculator
 * Created by Piotrek on 08-01-2016.
 *
 * Wyjątek wyrzucany, gdy wprowadzane dane są w porządku malejącym
 */
public class DecreasingDataOrderException extends Exception {
    public DecreasingDataOrderException(String msg) {
        super(msg);
    }
}
