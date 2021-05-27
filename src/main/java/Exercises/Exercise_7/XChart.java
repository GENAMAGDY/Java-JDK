/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author original
 */
public class XChart {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        
        //Read data from json file
        HelperFunctions helperFn = new HelperFunctions();
        List<TitanicPassenger> allPassengers = getPassengersFromJsonFile("src/main/resources/data/titanic_csv.json");
        
        //CategoryChart for Passenger Agesand Names
        helperFn.graphCategoryChart_PassengerAges (allPassengers);
        
        //CategoryChart for Passenger Ages and Fares
        helperFn.scatter_PassengerAgesAndFares(allPassengers);
        
        helperFn.PieChart_PassengerAgesAndFares(allPassengers);
        
        
    }
    
     // Read json file and return a list containing TitanicPassenger Objects
    public static List<TitanicPassenger> getPassengersFromJsonFile(String fileName) {
        List<TitanicPassenger> allPassengers = new ArrayList<> ();
        ObjectMapper objectMapper = new ObjectMapper ();
        objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            InputStream input = new FileInputStream (fileName);
            try {
                allPassengers = objectMapper.readValue (input, new TypeReference<List<TitanicPassenger>>() {});
            } catch (IOException ex) {
                Logger.getLogger(XChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XChart.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return allPassengers;
    }
    
    


    
}
