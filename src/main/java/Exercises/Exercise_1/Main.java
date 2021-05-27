/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_1;

import java.util.ArrayList;
import java.util.List;

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
       PyramidCSVDAO obj = new PyramidCSVDAO();
       List<Pyramid> l;
       l = new ArrayList<>();
       l= obj.readPyramidsFromCSV("src/main/resources/data/pyramids.csv");
       for(Pyramid p : l)
       {
          System.out.println(p);
       }
    }
}
