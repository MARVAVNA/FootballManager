package custom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomFiles {
    public static Object[] read(String url) throws IOException {
        return Files.readAllLines(Paths.get(url)).toArray();
    }
}
