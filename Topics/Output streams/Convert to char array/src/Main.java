import java.io.CharArrayWriter;
import java.io.IOException;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        CharArrayWriter writer = new CharArrayWriter();

        for (String word : words) {
            writer.append(word);
        }

        char[] wordsAsChars = writer.toCharArray();
        writer.close();

        return wordsAsChars;
    }
}