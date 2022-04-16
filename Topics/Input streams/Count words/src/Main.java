import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int current = reader.read();
        int numberOfWords = 0;
        boolean isWord = false;

        while (current != -1) {
            if ((char) current != ' ') {
                if (!isWord) {
                    numberOfWords++;
                }
                isWord = true;
            } else {
                isWord = false;
            }
            current = reader.read();
        }
        System.out.println(numberOfWords);
        reader.close();
    }
}