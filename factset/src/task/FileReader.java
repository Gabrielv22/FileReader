package task;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    String readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[10];
        StringBuilder sb = new StringBuilder();
        while (fis.read(buffer) != -1) {
            sb.append(new String(buffer));
            buffer = new byte[10];
        }
        fis.close();
        return sb.toString();
    }
}
