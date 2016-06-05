package in.healthycoder.tnea.college;

import in.healthycoder.tnea.CsvUtility;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/27/2016.
 */
public class CollegeReader {
    public Stream<College> readColleges() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("colleges2015.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.lines()
                .skip(1)
                .map(College::new);
    }

    public static void main(String[] args) throws IOException {
        CsvUtility utility = new CsvUtility();
        String fileName = utility.writeHeader("colleges", College.HEADERS);
        CollegeReader collegeReader = new CollegeReader();
        utility.writeToFile(fileName, collegeReader.readColleges());
    }
}
