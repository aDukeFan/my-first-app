public class Converter {
    public int convertToKm(int steps) {
        return steps * 75 / 100000;
    }

    public int convertStepsToKilocalories(int steps) {
        return steps * 50 / 1000;
    }
}
