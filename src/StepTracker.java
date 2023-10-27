import java.util.Scanner;

class StepTracker {
    Scanner scanner;
    MonthData[] monthToData = new MonthData[12];
    Converter converter = new Converter();

    StepTracker(Scanner scan) {
        scanner = scan;
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumbersStepsPerDay() {
        System.out.println("Введите номер месяца");
        int userMonth = scanner.nextInt();
        System.out.println("Введите день от 1 до 30 (включительно)");
        int userDay = scanner.nextInt();
        System.out.println("Введите количество шагов");
        int userSteps = scanner.nextInt();
        if ((userMonth > 0) && (userMonth < 13) && (userDay > 0) && (userDay < 31) && (userSteps > 0)) {
            MonthData monthData = monthToData[userMonth - 1];
            monthData.days[userDay - 1] = userSteps;
            System.out.println("Изменения сохранены. В " + userMonth + "-ом месяце " + userDay + "-го числа Вы прошли: " + userSteps + " шагов.");
        } else {
            System.out.println("Некорретный ввод данных, попробуйте еще раз.");
        }
    }

    int goalByStepsPerDay = 10000;

    void changeStepGoal() {
        System.out.println("Введите желаемую цель (шагов в день)");
        int userTarget = scanner.nextInt();
        if (userTarget > 0) {
            goalByStepsPerDay = userTarget;
            System.out.println("Цель изменена. Ваша новая цель: " + goalByStepsPerDay + " шагов в день.");
        } else {
            System.out.println("Некорретный ввод данных, попробуйте еще раз.");
        }
    }

    void printStatistic() {
        System.out.println("Введите число месяца");
        int userMonth = scanner.nextInt();
        if ((userMonth > 0) && (userMonth < 13)) {
            MonthData monthData = monthToData[userMonth - 1];
            monthData.printDaysAndStepsFromMonth();// вывод общей статистики по дням
            int sumSteps = monthData.sumStepsFromMonth();
            System.out.println("Сумма шагов за месяц: " + sumSteps);// вывод суммы шагов за месяц
            int maxSteps = monthData.maxSteps();
            System.out.println("Максимальное количество пройденных шагов за месяц: " + maxSteps);// вывод максимального пройденного количества шагов за месяц
            System.out.println("В среднем пройдено шагов за месяц: " + sumSteps / 30);// вывод среднего пройденного количества шагов за месяц
            int distance = converter.convertToKm(sumSteps);
            System.out.println("Пройденная дистанция за месяц, км: " + distance);// вывод пройденной за месяц дистанции в км
            int kcal = converter.convertStepsToKilocalories(sumSteps);
            System.out.println("Количество сожженых килокалорий за месяц: " + kcal);// вывод количества сожжённых килокалорий за месяц
            int bestSeries = monthData.bestSeries(goalByStepsPerDay);
            System.out.println("Лучшая серия шагов, дней: " + bestSeries);// вывод лучшей серии
            System.out.println(); //дополнительный перенос строки
        } else {
            System.out.println("Некорретный ввод данных, попробуйте еще раз.");
        }
    }
}
