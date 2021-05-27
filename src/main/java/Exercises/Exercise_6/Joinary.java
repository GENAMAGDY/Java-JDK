package Exercises.Exercise_6;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import joinery.DataFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author original
 */
public class Joinary {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        //Joinery on integer columns
        String path ="src/main/resources/data/titanic.csv";
        System.out.println("Joinery");
        DataFrame<Object>  df;
        try {
            
            //Joinary on integer data
            df = DataFrame.readCsv (path).retain("survived","age","sibsp","parch","ticket","fare");
            //Mean
            System.out.println("Mean :");
            df.mean().iterrows ().forEachRemaining (System.out::println);
            
            //min
            System.out.println("Min :");
            df.min().iterrows ().forEachRemaining (System.out::println);
            
            //max
            System.out.println("Max :");
            df.max().iterrows ().forEachRemaining (System.out::println);
            
            //standard deviation
            System.out.println("standard deviation:");
            df.stddev().iterrows ().forEachRemaining (System.out::println);
            
            
             DataFrame df_1 = DataFrame.readCsv (path).retain("name","sex");
            
            DataFrame df_2 = DataFrame.readCsv (path).retain("home.dest");
            
            
            //Join Two DataFrames
            DataFrame join= df_1.join(df_2,DataFrame.JoinType.INNER);
            System.out.println("DataFrame After Joining : ");
            System.out.println(join);
           
            //Merge Two DataFrames
            
            
            //add new
            List<Object> embarkedColumn =DataFrame.readCsv (path).col("embarked");
            DataFrame<Object> new_df = df_1.add("embarked", embarkedColumn );
            System.out.println("DataFrame After adding column (embarked) : ");
            System.out.println(new_df);
            
        } catch (IOException ex) {
            Logger.getLogger(Joinary.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
