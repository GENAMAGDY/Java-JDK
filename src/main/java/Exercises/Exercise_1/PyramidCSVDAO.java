/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_1;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author original
 */
public class PyramidCSVDAO {
     public PyramidCSVDAO()
    {
    }
    
    public Pyramid createPyramid(String[] metadata)
    {   
        double calHeight;
        if ("".equals(metadata[7]))
        {
            calHeight= 0;
        }
        else
        {
           calHeight = Double.parseDouble(metadata[7]);
        }
        Pyramid obj = new Pyramid(metadata[0], metadata[2],metadata[4],calHeight) ;
        return obj;
    }
      
    
    public List<Pyramid> readPyramidsFromCSV(String fileName)
    {
        List<Pyramid> listPyramid = new ArrayList<Pyramid>();
        File f = new File(fileName);
        List<String> lines = new ArrayList<String>();
        try{
            lines= Files.readAllLines(f.toPath());
        }
        catch(Exception e)
        {
            System.out.println("Exception :"+e);
        }
        for(int i=1;i<lines.size();i++)
        {
           String line = lines.get(i);
           String[] metadata = line.split(",");
           Pyramid p = createPyramid(metadata);
           listPyramid.add(p);
           
        }
        return listPyramid;
    }
    
}
