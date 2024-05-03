import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker tracker = new StepTracker(scanner);
        System.out.println("Welcome to StepTracker!");
        while (true) {
            printMenu();
            int point = scanner.nextInt();
            switch (point) {
                case 1:
                    tracker.addNewNumbersStepsPerDay();
                    break;
                case 2:
                    tracker.changeStepGoal();
                    break;
                case 3:
                    tracker.printStatistic();
                    break;
                case 4:
                    System.out.println("Have a nice day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Oopse! There is no such point =(");
            }
        }
    }

    private static void printMenu() {
        System.out.println("--------------------\n " +
                "1 - enter the number of steps for a certain day\n " +
                "2 - change your daily steps goal\n " +
                "3 - get statistics for certain month\n " +
                "4 - Close the app\n" +
                "--------------------");
    }
}
