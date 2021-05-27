/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author original
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
            String fileName = "src/main/resources/data/pyramids.csv";
        
        List<Double> dataSorted = readDataFromCSV(fileName).stream().sorted().collect(Collectors.toList());
        int size = dataSorted.size();
        System.out.println("Size of Data = "+size);
        int indexMedian = median(0,size);
        System.out.println("Index of Median = "+indexMedian+ " and Value = "+dataSorted.get(indexMedian) );
        int indexFirstQuartile = median(0, indexMedian);
        System.out.println("Index of first Quartile = " + indexFirstQuartile +" and Value = "+dataSorted.get(indexFirstQuartile));
        int indexSecondQuartile = median(indexMedian+1 , size);
        System.out.println("Index of Second Quartile = " + indexSecondQuartile+" and Value = "+dataSorted.get(indexSecondQuartile));
        System.out.println("InterQuartile = " + (dataSorted.get(indexSecondQuartile)-dataSorted.get(indexFirstQuartile)));
            
     
    }
    
    static List<Double> readDataFromCSV(String fileName)
    {
        File f =new File(fileName);
        List<String> lines;
        List<Double> data = new ArrayList();
        try {
            lines = Files.readAllLines(f.toPath());
            for(int i =1 ; i<lines.size() ;i++)
            {
                String[] splits = lines.get(i).split(",");
                if(!splits[7].isEmpty())
                {
                    data.add(Double.parseDouble(splits[7]));
                } 
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
        
    }
    static int median(int StartIndex, int r)
    {
       int n = r - StartIndex + 1;
       n = (n + 1) / 2 - 1;
       return n + StartIndex;
    }
    
}
