import java.util.List;
import java.util.Scanner;

public class StepTracker {
    private final Scanner scanner;
    private final MonthData[] monthToData = new MonthData[12];
    private final Converter converter = new Converter();
    private int goalByStepsPerDay = 10000;

    StepTracker(Scanner scanner) {
        this.scanner = scanner;
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    public void addNewNumbersStepsPerDay() {
        System.out.println("please, enter a month number");
        int userMonth = scanner.nextInt();
        if ((userMonth > 0) && (userMonth < 13)) {
            System.out.println("data saved successfully");
        } else {
            System.out.println("wrong number, sorry");
            return;
        }

        System.out.println("please, enter a day number (1 to 30)");
        int userDay = scanner.nextInt();
        while ((userDay < 1) || (userDay > 30)) {
            System.out.println("wrong number, sorry");
            userDay = scanner.nextInt();
        }
        System.out.println("please, enter a number of steps");
        int userSteps = scanner.nextInt();
        while (userSteps < 1) {
            System.out.println("wrong number, sorry");
            userSteps = scanner.nextInt();
        }

        List<String> months = List.of(
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December");
        System.out.printf("date saved successfully. you walked %d steps on the %d %s \n",
                userSteps, userDay, months.get(userMonth - 1));
        MonthData monthData = monthToData[userMonth - 1];
        monthData.days[userDay - 1] = userSteps;
    }

    public void changeStepGoal() {
        System.out.println("previous goal is " + goalByStepsPerDay + " steps per day");
        System.out.println("please, enter your new goal");
        int userTarget = scanner.nextInt();
        if (userTarget > 0) {
            goalByStepsPerDay = userTarget;
            System.out.printf("your new goal is %d per day\n", goalByStepsPerDay);
        } else {
            System.out.println("sorry, but wrong steps number, your goal isn't changed");
        }
    }

    public void printStatistic() {
        System.out.println("please, enter a month number");
        int userMonth = scanner.nextInt();
        if ((userMonth > 0) && (userMonth < 13)) {
            MonthData monthData = monthToData[userMonth - 1];
            monthData.printDaysAndStepsFromMonth();
            int sumSteps = monthData.sumStepsFromMonth();
            int maxSteps = monthData.maxSteps();
            int distance = converter.convertToKm(sumSteps);
            int kcal = converter.convertStepsToKilocalories(sumSteps);
            int bestSeries = monthData.bestSeries(goalByStepsPerDay);
            System.out.printf("total steps per month: %d\n" +
                    "total distance per month: %d км\n" +
                    "Max steps per day: %d\n" +
                    "average value of steps per day: %d\n" +
                    "total kilo calories burned: %d\n" +
                    "best series of steps: %d day(s)\n",
                    sumSteps, distance, maxSteps, sumSteps / 30, kcal, bestSeries);
        } else {
            System.out.println("sorry, but wrong number");
        }
    }
}
