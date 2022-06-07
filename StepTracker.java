import java.util.Scanner;
public class StepTracker {
    Scanner scanner = new Scanner(System.in);
    Converter converter = new Converter();
    MonthData[] monthToData;
    int goalStep = 10000;

    public StepTracker() { // объявили метод StepTracker; теперь создаем 12 месяцев по 30 дней  в каждом
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData(); // содержит объект с массивом на 30 дней
        }
    }

    public void saveSteps() { // сохраняет шаги за определённый день
        int monthNumber;
        int dayNumber;
        int stepsNumber;
        while (true) {
            System.out.println("Выберите месяц, за который хотите ввести количество шагов. " +
                    "Доступны следующие комманды: " +
                    "0-Январь; 1-Февраль; 2-Март; " + "3-Апрель; 4-Май; 5-Июнь); 6-Июль;" +
                    " 7-Август; 8-Сентябрь; 9-Октябрь; 10-Ноябрь; 11-Декабрь");
            monthNumber = scanner.nextInt();
            if (monthNumber < 0) {
                System.out.println("Введено неверное значение! Число не должно быть отрицательным.");
            } else if (monthNumber > 11) {
                System.out.println("Введено неверное значение! Число не должно быть больше 11.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Выберите день. Введите число от 0 до 29");
            dayNumber = scanner.nextInt();
            if (dayNumber < 0) {
                System.out.println("Введено неверное значение! Число не должно быть отрицательным.");
            } else if (dayNumber > 29) {
                System.out.println("Введено неверное значение! Число не должно быть больше 29.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Введите количество шагов, пройденных в этот день.");
            stepsNumber = scanner.nextInt();
            if (stepsNumber < 0) {
                System.out.println("Введено неверное значение! Число не должно быть отрицательным.");
            } else {
                break;
            }
        }
        monthToData[monthNumber].stepsAtDay(dayNumber, stepsNumber);
        System.out.println("Значение сохранено! Всего в этот день вы прошли: " + stepsNumber + " шагов(а).");
    }

    public void statMonth() { // показывает статистику (шаги, ккал, км)
        int monthNumber;
        while (true) {
            System.out.println("Выберите месяц, за который хотите посмотреть статистику:");
            monthNumber = scanner.nextInt();
            if (monthNumber < 0) {
                System.out.println("Введено неверное значение! Число не должно быть отрицательным.");
            } else if (monthNumber > 11) {
                System.out.println("Введено неверное значение! Число не должно быть больше 11.");
            } else {
                break;
            }
        }
        monthToData[monthNumber].stepsAtMonth(); // выводит список дней и шагов
        monthToData[monthNumber].sumSteps(); //суммирует все шаги за месяц/
        System.out.println("Максимальное количество шагов, пройденных в день, за этот месяц составило: " +
                monthToData[monthNumber].findMaxSteps()); // максимальное количество шагов в день
        System.out.println("Среднее количество шагов, пройденных в этом месяце, составило: " +
                monthToData[monthNumber].findAvgSteps()); // среднее значение за месяц
        System.out.println("Лучшая серия в этом месяце:" + monthToData[monthNumber].findBestCombo(goalStep) + " идущих дня/дней подряд.");
        System.out.println("Так держать!");
    }

    public void setGoalStep() { // позволяет изменить целевое количество шагов
        System.out.println("Целевое количество шагов: " + goalStep + ".");
        System.out.println("Введите новое целевое количество шагов:");
        int newGoalStep = scanner.nextInt();
        while (true) { // цикл для проверки правильности вводв пользователя
            if (newGoalStep < 0) {
                System.out.println("Введено неверное значение! Число шагов не должно быть отрицательным.");
                System.out.println("Введите новое целевое количество шагов:");
                newGoalStep = scanner.nextInt();
            }
            goalStep = newGoalStep;
            System.out.println("Новое целевое количество шагов сохранено!");
            break;
        }
    }


    class MonthData { // Этот класс - заготовка для месяцев! что он делает? создает отдельный месяц! массив на 30 дней
        int[] month = new int[30];

        public void stepsAtDay(int day, int steps) { // добавляет шаги в выбранный массив
            month[day] = steps;
        }

        public void stepsAtMonth() { // выводит список дней с пройденными шагами
            System.out.println("Количество пройденных шагов по дням за выбранный месяц: ");
            for (int i = 0; i < month.length; i++) {
                System.out.println((i + 1) + " день: " + month[i] + " шагов(а).");
            }
        }

        public void sumSteps() {  // считает все шаги и показыыает результат
            Converter converter = new Converter();
            int sum = 0;

            for (int i = 0; i < month.length; i++) {
                sum = sum + month[i];
            }
            System.out.println("Всего в этом месяце пройдено: " + sum + " шагов(а).");
            System.out.println("В этом месяце вы прошли " + converter.findDistance(sum) + " км.");
            System.out.println("В этом месяце вы сожгли " + converter.findKKal(sum) + " кКал.");
        }

        public int findMaxSteps() { // покажет максимальное количество шагов в выбранном месяце{
            int maxSteps = 0;
            for (int i = 0; i < month.length; i++) {
                if (month[i] > maxSteps) {
                    maxSteps = month[i];
                }
            }
            return maxSteps;
        }

        public double findAvgSteps() { // находит среднее количество шагов
            double avgSteps = 0;
            for (int i = 0; i < month.length; i++) {
                avgSteps = avgSteps + month[i];
            }
            return (avgSteps / month.length);
        }

        public int findBestCombo(int goal) { // лучшая серия подряд идущих дней
            int bestCombo = 0;
            int counter = 0;
            for (int i = 0; i < month.length; i++) {
                if (month[i] >= goal) {
                    counter = counter + 1;
                } else  {
                    if (counter > bestCombo) {
                        bestCombo = counter;
                    }
                    counter = 0;
                }
            }
            return bestCombo;
        }

    }

    public class Converter {  // В этом классе осуществляется преобразование шагов в километры и калории.
        double step = 0.75; // метров в одном шаге
        double kKal = 0.05; // количество килокалорий за один шаг

        public double findDistance(int stepsAmount) { // переводит шаги в километры
            double distance =  stepsAmount * step / 1000;
            return distance;
        }

        public double findKKal(int stepsAmount) { // считает килокалории
            double burntKKal =  stepsAmount * kKal;
            return burntKKal;
        }
    }

}


