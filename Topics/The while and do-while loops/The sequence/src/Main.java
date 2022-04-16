import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputAmount = scanner.nextInt();
        int output = 0;

        while (inputAmount != 0) {
            int input = scanner.nextInt();
            if (input % 4 == 0 && input > output) {
                output = input;
            }
            inputAmount--;
        }
        
        System.out.println(output);
    }
}