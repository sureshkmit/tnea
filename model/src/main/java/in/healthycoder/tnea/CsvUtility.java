package in.healthycoder.tnea;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/24/2016.
 */
public class CsvUtility {
    public String writeHeader(String fname, String headers) throws IOException {
        String fileName = fname + "-" + System.currentTimeMillis() + ".csv";
        FileWriter writer = new FileWriter(fileName);
        writer.write(headers + "\n");
        writer.close();
        return fileName;
    }

    public void writeToFile(String fileName, Stream<? extends Object> objects) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        objects.forEach(object -> {
            try {
                writer.append(object.toString());
                writer.append("\n");
                System.out.println(object.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}
