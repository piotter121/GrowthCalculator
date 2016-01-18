package growthCalculator.calculator;

import growthCalculator.calculator.growthCharts.BoysHeightGrowthChart;
import growthCalculator.calculator.growthCharts.GrowthChart;
import growthCalculator.exceptions.CalculationException;
import growthCalculator.exceptions.DecreasingDataOrderException;

import java.util.*;

/**
 * GrowthCalculator
 * Created by Piotrek on 22-12-2015.
 *
 * Główna klasa odpowiedzialna za przyjmowanie danych i obliczenie przyszłej wagi/wzrostu dla dziecka.
 * Rozszerza ona klasę Observable, aby móc powiadamiać graficzny interfejs o zmianach, które zaszły w niej.
 */
public class GrowthCalculator extends Observable{
    /** zmienna do przechowywania danych wprowadzonych przez użytkownika */
    private SortedMap<Integer, Double> userData;

    /** w tej zmiennej przechowywane są oszacowane przyszłe waga lub wzrost */
    private SortedMap<Integer, Double> result;

    /** instancja aktualnie wybranej siatki centylowej */
    private GrowthChart chart;

    /** konstruktor klasy, nie przyjmujący argumentów */
    public GrowthCalculator() {
        userData = new TreeMap<>(Integer::compareTo);
        result = new TreeMap<>(Integer::compareTo);
        chart = new BoysHeightGrowthChart();
    }

    /** Publiczna metoda pozwalająca zmienić aktualną siatkę centylową i w ten sposób zmienić opcje obliczeń */
    public void setGrowthChart(GrowthChart chart) {
        this.chart = chart;

        /** Powiadomienie obserwatorów o zmianie opcji */
        notifyObservers();
    }

    /** Publiczna metoda zwracająca aktualną siatkę centylową */
    public GrowthChart getGrowthChart() {
        return chart;
    }

    /** Publiczna metoda zwracająca dane wprowadzone przez użytkownika */
    public SortedMap<Integer, Double> getUserData() {
        return userData;
    }

    /** Publiczna metoda zwracająca dane oszacowane przez kalkulator */
    public SortedMap<Integer, Double> getCalculationResult() {
        return result;
    }

    /** Publiczna metoda zwracająca prawdę jeśli w kalkulatorze znajdują się jakieś dane, np. do wyświetlenia na wykresie,
    * lub fałsz, gdy kalkulator jest pusty */
    public boolean hasData() {
        return !userData.isEmpty() || !result.isEmpty();
    }

    /** Wstawia do kalkulatora wiek wraz z przypisaną do niego wartością, którą może być wzrost lub waga.
     * Wyrzuca wyjątek IllegalArgumentException, gdy wiek nie mieści się w przedziale od 1 do 18 lat, lub gdy
     * wprowadzana wartość jest niedodatnia.
     * Wyrzuca wyjątek DecreasingDataOrderException, gdy wprowadzane są malejące wobec już wprowadzonych. */
    public void set(int age, double value) throws IllegalArgumentException, DecreasingDataOrderException {
        if (age < 1) throw new IllegalArgumentException("Zbyt niski wiek - " + age);
        if (age > 18) throw new IllegalArgumentException("Zbyt wysoki wiek - " + age);
        if (value <= 0) throw new IllegalArgumentException("Niedodatnia wartość " + value);

        userData.put(age, value);
        ArrayList<Double> values = new ArrayList<>(userData.values());
        for (int i = 0; i < (values.size() - 1); i++) {
            if (values.get(i) > values.get(i + 1)) {
                userData.remove(age);
                throw new DecreasingDataOrderException("Wartości wprowadzone są niepoprawne");
            }
        }

        /** Powiadomienie obserwatorów o zmianach */
        notifyObservers();
    }

    /** Oblicza przyszłą wagę lub wzrost dla dziecka.
     * Wyrzuca wyjątek CalculationException, gdy wprowadzone dane nie mieszczą się między 3 a 97 centylem. */
    public void calculate() throws CalculationException {
        if (!userData.isEmpty() && result.isEmpty()) {
            List<Integer> matchedPercentiles = new ArrayList<>(userData.size());

            /** Sprawdzanie pomiędzy jakimi centylami znajdują się wpisane wartości */
            for (Integer age : userData.keySet())
                matchedPercentiles.add(chart.matchToPercentile(age, userData.get(age)));

            /** Ustawienie wag */
            List<Integer> wages = new ArrayList<>(matchedPercentiles.size());
            for (int i = 1; i <= matchedPercentiles.size(); i++) wages.add(i);

            /** Obliczenie średniego ważonego centyla dla wprowadzonych danych */
            int average = calculateWeightedAverage(matchedPercentiles, wages);

            if (average < 3 || average > 97)
                throw new CalculationException("Rozwój dziecka nie mieści się w granicach między 3 a 97 centylem!");

            /** Obliczenie 5 wartości na przód */
            int startPoint = userData.lastKey() + 1;
            for (int i = startPoint; i < (startPoint + 5) && i < 19; i++)
                setResult(i, findValue(i, average));
        }
    }

    /** Pomocnicza funkcja licząca średnią ważoną na podstawie podanej listy wartości i listy ich wag */
    private int calculateWeightedAverage(List<Integer> values, List<Integer> wages) {
        double sumOfValuesMultipliedByWages = 0;
        double sumOfWages = 0;
        final int count = values.size();
        for (int i = 0; i < count; i++) {
            sumOfValuesMultipliedByWages += values.get(i) * wages.get(i);
            sumOfWages += wages.get(i);
        }
        return (int) Math.round(sumOfValuesMultipliedByWages / sumOfWages);
    }


    private void setResult(int age, double val) {
        result.put(age, val);
        notifyObservers();
    }

    /** Pomocnicza funkcja odczytująca wartość z siatki centylowej dla zadanego wieku i centyla */
    private double findValue(int age, int anotherPercentile) {
        /** Lista centyli znajdujących się już w siatce centylowej */
        List<Integer> percentiles = chart.getPercentilesList();

        /** Szukanie, który z znajdujących się już w siatce centylowej centyli znajduje się najbliżej podanego jako
        * argument */
        List<Integer> abs = new ArrayList<>(percentiles.size());
        for (Integer percentile: percentiles) abs.add(Math.abs(percentile - anotherPercentile));
        int index;
        int matched = percentiles.get((index = abs.indexOf(Collections.min(abs))));

        int upperPercentile, lowerPercentile;
        if (anotherPercentile >= matched && matched != percentiles.get(percentiles.size()-1)) {
            lowerPercentile = matched;
            upperPercentile = percentiles.get(index + 1);
        } else {
            upperPercentile = matched;
            lowerPercentile = percentiles.get(index - 1);
        }

        /** Zwracana wartość jest zaokrąglana do dwóch miejsc po przecinku */
        return (double) Math.round((chart.getValueAt(age, lowerPercentile) + ((chart.getValueAt(age, upperPercentile)
                - chart.getValueAt(age, lowerPercentile)) / (upperPercentile - lowerPercentile))
                * (anotherPercentile - lowerPercentile)) * 100.0) / 100.0;
    }

    public void remove(int age) {
        if (userData.containsKey(age))
            userData.remove(age);
        if (result.containsKey(age))
            result.remove(age);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
