import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int largestNumber = 0;

        int input;
        do {
            input = scanner.nextInt();
            if (input > largestNumber) {
                largestNumber = input;
            }
        } while (input != 0);

        System.out.println(largestNumber);
    }
}