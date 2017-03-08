import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.csv.*;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.SimpleIntensityDownsample;

public class DataParse {
    public double[][] input;
    public double [][] ideal;
    public DataParse(String fileName) throws IOException{

        CSVParser parser = CSVParser.parse(new File(fileName), Charset.defaultCharset(), CSVFormat.DEFAULT);
        java.util.List<CSVRecord> csvFile = parser.getRecords();
        //parser.close();
        input = new double[csvFile.size()][10];
        ideal = new double[csvFile.size()][1];
        for (int i = 0; i < csvFile.size(); i++) {
            for (int j = 0; j < 10; j++) {
                input[i][j] = Double.parseDouble(csvFile.get(i).get(j));

            }
            ideal[i][0] = Double.parseDouble(csvFile.get(i).get(10));
            /*
            if (csvFile.get(i).get(4).contains("virginica")){
                ideal[i][0] = 0;
            } else if (csvFile.get(i).get(4).contains("versicolor")){
                ideal[i][0] = 1.5;
            } else if (csvFile.get(i).get(4).contains("setosa")){
                ideal[i][0] = 3;
            }
            */
        }
        normalize();
        ImageMLDataSet imageData = new ImageMLDataSet(new SimpleIntensityDownsample(),true,20,20);
    }

    void normalize(){
        double maxValueInput = 13;
        double maxValueIdeal = 9;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = input[i][j]/maxValueInput;
            }
        }
        for (int i = 0; i < ideal.length; i++) {
            for (int j = 0; j < ideal[i].length; j++) {
                ideal[i][j] = ideal[i][j]/maxValueIdeal;
            }
        }
    }
}
