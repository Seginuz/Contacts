import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern passwordPattern = Pattern.compile("password(\\s|:)*([a-zA-Z0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(text);

        int passwordsFound = 0;
        while (passwordMatcher.find()) {
            System.out.println(passwordMatcher.group(2));
            passwordsFound++;
        }

        if (passwordsFound == 0) {
            System.out.println("No passwords found.");
        }
    }
}