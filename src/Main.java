import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Лабораторная работа: Матрицы комплексных чисел");


        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Ваш выбор: ");


            try {
                switch (choice) {
                    case 1:
                        performAddition();
                        break;
                    case 2:
                        performMultiplication();
                        break;
                    case 3:
                        performTranspose();
                        break;
                    case 4:
                        performDetermination();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Выход из программы. До свидания!");
                        break;
                    default:
                        System.out.println("Неверный пункт меню. Попробуйте сноваю \n");
                }
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                System.out.println("Ошибка: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }


    private static void printMenu() {
        System.out.println("Выберите операцию: ");
        System.out.println("1. Сложение матриц");
        System.out.println("2. Умножение матриц");
        System.out.println("3. Транспонирование матрицы");
        System.out.println("4. Определитель матрицы");
        System.out.println("0. Выход");
    }


    private static void performAddition() {
        System.out.println("\n Сложение матриц: ");
        Matrix a = readMatrix("A");
        Matrix b = readMatrix("B");


        Matrix result = a.add(b);
        System.out.println("\n Результат (А + В): ");
        result.print();
        System.out.println();
    }


    private static void performMultiplication() {
        System.out.println("\n Умножение матриц:");
        System.out.println("Напоминание: количество столобцов А должно равняться количеству строк В");
        Matrix a = readMatrix("A");
        Matrix b = readMatrix("B");


        Matrix result = a.multiply(b);
        System.out.println("\n Результат умножения А и В: ");
        result.print();
        System.out.println();

    }


    private static void performTranspose() {
        System.out.println("Транспонирование матрицы: ");
        Matrix a = readMatrix("A");

        Matrix result = a.transpose();
        System.out.println("\nРезультат (A^T):");
        result.print();
        System.out.println();
    }


    private static void performDetermination() {
        System.out.println("\nОпределитель ");
        System.out.println("(Матрица должна быть квадратной)");
        Matrix a = readMatrix("A");

        ComplexNumber det = a.determinant();
        System.out.println("\nОпределитель матрицы A = " + det);
        System.out.println();
    }


    private static Matrix readMatrix(String name) {
        System.out.println("Ввод матрицы " + name + ":");
        int rows = readInt("Введите количество строк: ");
        int cols = readInt("Введите количество столбцов: ");


        Matrix matrix = new Matrix(rows, cols);


        System.out.println("Введите элементы матрицы. Формат: <действительная> <мнимая>. Пример: '3 4'-> 3 + 4i. ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(" " + name + "[" + i + "][" + j + "]: ");
                double real = readDouble("");
                double imag = readDouble("");
                matrix.setElement(i, j, new ComplexNumber(real, imag));
            }
        }
        System.out.println("Матрица " + name + " введена.\n");
        return matrix;
    }


    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно ввести целое число. Попробуйте снова.");
                scanner.nextLine();
            }
        }
    }


    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно ввести число. Попробуйте снова.");
                scanner.nextLine();
            }
        }
    }
}

