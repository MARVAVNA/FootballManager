package custom;

import exception.IndexException;

public class CustomString {
    public static String replaceAllSpacesByEmpty(String sentences) {
        if (sentences == null) {
            return null;
        }

        return sentences.replaceAll(" ", "");
    }

    public static String replaceAllLineBreaksByEmpty(String sentences) {
        if (sentences == null) {
            return null;
        }

        return sentences.replaceAll("(\\r|\\n)*", "");
    }

    public static String getSplitIndex(String line, String separator, int index) throws IndexException {
        if (index < 0) {
            throw new IndexException(index);
        }

        String[] words = line.split(separator);

        if (index >= words.length) {
            throw new IndexOutOfBoundsException();
        }

        return words[index];
    }
}
