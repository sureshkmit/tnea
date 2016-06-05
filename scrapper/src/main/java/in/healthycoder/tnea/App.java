package in.healthycoder.tnea;

import in.healthycoder.tnea.college.CollegeReader;
import in.healthycoder.tnea.cutoff.Cutoff;
import in.healthycoder.tnea.cutoff.CutoffExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        CollegeReader collegeReader = new CollegeReader();
        CutoffExtractor extractor = new CutoffExtractor();

        CsvUtility utility = new CsvUtility();
        String fileName = utility.writeHeader("cutoff", Cutoff.HEADERS);

        collegeReader.readColleges().forEach(college -> {
            Collection<Cutoff> cutoff = extractor.getCutoff(college);
            try {
                utility.writeToFile(fileName, cutoff.stream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public static void main1(String[] args) throws FileNotFoundException {
        CollegeReader collegeReader = new CollegeReader();
        collegeReader.readColleges().forEach(college -> System.out.println(college.getName()));
    }
}
