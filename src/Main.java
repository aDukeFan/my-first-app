import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker Steptracker = new StepTracker(scanner);

        while (true) {
            printMenu();
            int point = scanner.nextInt();
            if (point == 1) {
                Steptracker.addNewNumbersStepsPerDay();
            } else if (point == 2) {
                Steptracker.changeStepGoal();
            } else if (point == 3) {
                Steptracker.printStatistic();
            } else if (point == 4) {
                System.out.println("Всего хорошего!");
                scanner.close();
                return;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    static void printMenu() {
        System.out.println("Чего изволите?");
        System.out.println("1 - ввести количество шагов за определённый день");
        System.out.println("2 - изменить цель по количеству шагов в день");
        System.out.println("3 - напечатать статистику за определённый месяц");
        System.out.println("4 - выйти из приложения");
    }
}
