package fileExport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileExport {
    public static void fileExport(String content) throws IOException {
        String path = "A:/";
        int num = 1;
        String fileName = "Export" + num + ".txt";
        File file = new File(path + fileName);
        while (true) {
            if (!file.exists()) {
                file.createNewFile();
                break;
            } else {
                num++;
                fileName = "Export" + num + ".txt";
                file = new File(path + fileName);
            }
        }
        Files.write(Paths.get(path + fileName), content.getBytes());
    }
}


