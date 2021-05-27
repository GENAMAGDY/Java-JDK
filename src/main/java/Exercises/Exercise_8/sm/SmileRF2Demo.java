package Exercises.Exercise_8.sm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.data.vector.StringVector;
import smile.io.Read;
import smile.plot.swing.Histogram;

public class SmileRF2Demo {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException, URISyntaxException {
        //Read train data from titanic_train.csv
        DataFrame titanicTrain = Read.csv("src/main/resources/data/titanic_train.csv", CSVFormat.DEFAULT.withDelimiter(',')
                .withHeader("PassengerId","Survived","Pclass","Name","Sex","Age")
                .withSkipHeaderRecord(true));
        
       
       titanicTrain = preprocessing(titanicTrain);
       //eda(titanicTrain);
        
        
        //System.out.println(titanic.schema());
        //System.out.println(titanic.summary());
        RandomForest model = RandomForest.fit(Formula.lhs("Survived"), titanicTrain);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(model.importance()));
        System.out.println(model.metrics().accuracy);
        
        //TODO load test data to validate model
        DataFrame titanicTest = Read.csv("src/main/resources/data/titanic_test.csv", CSVFormat.DEFAULT.withDelimiter(',')
                .withHeader("PassengerId","Pclass","Name","Sex","Age")
                .withSkipHeaderRecord(true));
        
       String[] s =new String[titanicTest.size()];
       for(int i=0 ; i<titanicTest.size();i++)
       {
           s[i] = titanicTest.column("Pclass").get(i).toString();
       }
       titanicTest = titanicTest.drop("Pclass");
       titanicTest = titanicTest.merge(StringVector.of("Pclass", s));
       titanicTest = preprocessing(titanicTest);
       int[] predict = model.predict(titanicTest);
       System.out.println("Predict Data = "+ Arrays.toString(predict));

       
               
        
        
        
        
    }
    public static DataFrame preprocessing(DataFrame df)
    {
        df = df.merge(IntVector.of("PclassValue", encodeCategory(df, "Pclass")));
        df = df.merge(IntVector.of("SexValue", encodeCategory(df, "Sex")));
        df = df.drop("PassengerId");
        df = df.drop("Name");
        df = df.drop("Sex");
        df = df.drop("Pclass");
        df = df.omitNullRows();
        return df;
    }
    public static int[] encodeCategory(DataFrame df, String columnName) {
        String[] values = df.vector(columnName).distinct().toArray(new String[] {});
        int[] pclassValues = df.stringVector(columnName).factorize(new NominalScale(values)).toIntArray();
        return pclassValues;
    }
    
    
     
    private static void eda(DataFrame titanic) throws InterruptedException, InvocationTargetException {
        titanic.summary();
        DataFrame titanicSurvived = DataFrame.of(titanic.stream().filter(t -> t.get("Survived").equals(1)));
        DataFrame titanicNotSurvived = DataFrame.of(titanic.stream().filter(t -> t.get("Survived").equals(0)));
        titanicNotSurvived.omitNullRows().summary();
        titanicSurvived = titanicSurvived.omitNullRows();
        titanicSurvived.summary();
        int size = titanicSurvived.size();
        System.out.println(size);
        Double averageAge = titanicSurvived.stream()
                .mapToDouble(t -> t.isNullAt("Age" ) ? 0.0 : t.getDouble("Age"))
                .average()
                .orElse(0);
        System.out.println(averageAge.intValue());
        
        Map map = titanicSurvived.stream()
                .collect(Collectors.groupingBy(t -> Double.valueOf(t.getDouble("Age")).intValue(), Collectors.counting()));

        double[] breaks = ((Collection<Integer>)map.keySet())
                .stream()
                .mapToDouble(l -> Double.valueOf(l))
                .toArray();

        int[] valuesInt = ((Collection<Long>) map.values())
                .stream().mapToInt(i -> i.intValue())
                .toArray();

//    Histogram.of(titanicSurvived.doubleVector("Age").toDoubleArray(), values.length, true)
//          .canvas().setAxisLabels("Age","Count")
//          .setTitle("Age frequencies among surviving passengers" )
//          .window();
        Histogram.of(titanicSurvived.intVector("PclassValue").toIntArray(),4, true)
                .canvas().setAxisLabels("Classes","Count")
                .setTitle("Pclass values frequencies among surviving passengers" )
               .window();
        //Histogram.of(values, map.size(), false).canvas().window();
        titanicSurvived.schema();
    }
    
    
    
    
}