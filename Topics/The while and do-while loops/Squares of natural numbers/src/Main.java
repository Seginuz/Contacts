import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        int count = 1;

        while (count * count <= input) {
            System.out.println(count * count);
            count++;
        }
    }
}