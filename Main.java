import java.util.Scanner; import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        System.out.println("Здравствуйте!");
        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 0) { // обработка разных случаев

            if (userInput == 1) {
                stepTracker.saveSteps();
            } else if (userInput == 2) {
                stepTracker.statMonth();
            } else if (userInput == 3) {
                stepTracker.setGoalStep();
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Вы ввели 0 - Программа завершена.");
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");

    }
}
