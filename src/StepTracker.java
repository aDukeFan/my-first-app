import java.util.*;

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
        while ((userMonth < 1) || (userMonth > 12)) {
            System.out.println("Некорретный ввод, введите номер месяца");
            userMonth = scanner.nextInt();
        }
        System.out.println("Введите день от 1 до 30 (включительно)");
        int userDay = scanner.nextInt();
        while ((userDay < 1) || (userDay > 30)) {
            System.out.println("Некорректный ввод, введите день от 1 до 30 (включительно)");
            userDay = scanner.nextInt();
        }
        System.out.println("Введите количество шагов, их должно быть больше нуля");
        int userSteps = scanner.nextInt();
        while (userSteps < 1) {
            System.out.println("Некорректный ввод, введите количество шагов, их должно быть больше нуля");
            userSteps = scanner.nextInt();
        }
        /*Воспользовался твоим советом String.format():
        String text = String.format("Данные сохранены. %d числа %d месяца, Вы прошли %d шагов.", userDay, userMonth, userSteps);
        System.out.println(text);
        Но мне показалось, так еще проще:
        System.out.printf("Данные сохранены. %d числа %d месяца, Вы прошли шагов: %d.\n", userDay, userMonth, userSteps);
        а потом я решил добавить список с названиями месяцев: */
        List<String> months = new ArrayList<>();
        Collections.addAll(months, "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря");
        System.out.printf("Данные сохранены. %d-го %s, Вы прошли, шагов: %d.\n", userDay, months.get(userMonth - 1), userSteps);
        /* я бы еще добавил корректное количество дней в заданном месяце, но этого тоже в задании нет
         и тебе, наверное, не составит большого удовольствия это проверять,
         и вообще это приложение можно совершенствовать до бесконечности. */
        MonthData monthData = monthToData[userMonth - 1];
        monthData.days[userDay - 1] = userSteps;
    }

    int goalByStepsPerDay = 10000;

    void changeStepGoal() {
        // Добавил отображение прежней цели пользователю, а то вдруг потеряется:
        System.out.println("Прежняя цель составляла, шагов: " + goalByStepsPerDay);
        System.out.println("Введите желаемую цель (шагов в день)");
        int userTarget = scanner.nextInt();
        if (userTarget > 0) {
            goalByStepsPerDay = userTarget;
            System.out.printf("Цель изменена. Ваша новая цель: %d шагов в день.\n", goalByStepsPerDay);
        } else {
            System.out.println("Некорретный ввод данных, осталась прежняя цель, попробуйте еще раз.");
        }
    }

    void printStatistic() {
        System.out.println("Введите число месяца");
        int userMonth = scanner.nextInt();
        if ((userMonth > 0) && (userMonth < 13)) {
            MonthData monthData = monthToData[userMonth - 1];
            monthData.printDaysAndStepsFromMonth();
            int sumSteps = monthData.sumStepsFromMonth();
            int maxSteps = monthData.maxSteps();
            int distance = converter.convertToKm(sumSteps);
            int kcal = converter.convertStepsToKilocalories(sumSteps);
            int bestSeries = monthData.bestSeries(goalByStepsPerDay);
            // Здесь было слишком много print-ов, я изменил, и переносы в коде сделал для более удобной читаемости:
            System.out.printf("Cумма шагов за месяц: %d\n" +
                    "Дистанция в этот месяц составила: %d км\n" +
                    "Максимальное количество пройденных шагов в день: %d\n" +
                    "В среднем пройденно шагов: %d\n" +
                    "Вам удалось сжечь: %d ккал\n" +
                    "Лучшая серия шагов, дней: %d\n\n", sumSteps, distance, maxSteps, sumSteps / 30, kcal, bestSeries);
        } else {
            System.out.println("Некорретный ввод данных, попробуйте еще раз.");
        }
    }
}
