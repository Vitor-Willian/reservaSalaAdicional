import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida! Digite um número: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrada inválida! Digite um número: ");
            scanner.nextLine();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static void close() {
        scanner.close();
    }
}
