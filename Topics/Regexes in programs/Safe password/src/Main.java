import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine();

        String uppercaseRegex = ".*[A-Z]+.*";
        String lowercaseRegex = ".*[a-z]+.*";
        String digitRegex = ".*[0-9]+.*";
        String lengthRegex = ".{12,}";

        boolean containsUppercase = password.matches(uppercaseRegex);
        boolean containsLowercase = password.matches(lowercaseRegex);
        boolean containsDigit = password.matches(digitRegex);
        boolean isLongEnough = password.matches(lengthRegex);

        if (containsUppercase && containsLowercase && containsDigit && isLongEnough) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}