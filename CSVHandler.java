package triangleperimeter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CSVHandler {
    CSVReader reader;
    CSVWriter writer;
    String fileAddress = "C:\\Users\\Максим\\Desktop\\dots.csv";

    public ArrayList<int[]> readFile(String fileAddress) {
        ArrayList<int[]> result = new ArrayList<>();
        try {
            this.reader = new CSVReader(new FileReader(fileAddress), ',');
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        try {
            List<String[]> strings = reader.readAll();
            for (String[] strs : strings){
                int[] pair = new int[2];
                pair[0] = Integer.decode(strs[0]);
                pair[1] = Integer.decode(strs[1]);
                result.add(pair);
            }
        } catch (IOException ex) {
            System.out.println("Error while reading file");    
        }
        return result;
    }

    public void createNewSet(int num, String fileAddress){
        Random r = new Random();
        try {
            this.writer = new CSVWriter(new FileWriter(fileAddress), ',');
        } catch (IOException ex) {
            System.out.println("Error while writing to file");
        }
        for (int i = 0; i < num; i++){
            String[] next = new String[2];
            next[0] = Integer.toString(2*getBinomial(6400, 0.5) - 6000);
            next[1] = Integer.toString(2*getBinomial(6400, 0.5) - 6000);
            writer.writeNext(next);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to close file");
        }
    }
    
    public static int getBinomial(int n, double p) {
        int x = 0;
        for(int i = 0; i < n; i++) {
            if(Math.random() < p) x++;
        }
        return x;
    }   
}