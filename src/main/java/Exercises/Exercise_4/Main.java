/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_4;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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
        
        CityDAO cityDAO = new CityDAO();
        List<String> citiesLines= cityDAO.readDataFromCSV("src/main/resources/data/Cities.csv");
        List<City> cities = cityDAO.createCities(citiesLines);
        
        
        
        CountryDAO countryDAO = new CountryDAO();
        List<String> countryLines = countryDAO.readDataFromCSV("src/main/resources/data/Countries.csv");
        List<Country> countries = countryDAO.createCities(countryLines);
        
       //set of country in cities
       cityDAO.setCountryInCity(cities, countries);
       
       cityDAO.setCitiesOfCountry(cities, countries);
     
        
        
       /*
          Highest population city of each country
       */
       countries.forEach(country -> {
           int max = country.getCities().stream()
                   .mapToInt(city -> city.getPopultion())
                   .max()
                   .orElse(0);
            System.out.println("Hight population is "+max+" in "+ country.getName());
       });
       /*
          Highest population city by continent
       */
       
        Map<String, List<City>> groups =
                cities.stream().collect(groupingBy( c->c.getContinent(), toList()));
       
      
        
        groups.forEach((k,v)->{
           System.out.println( "Continent is "+k+ " and Highest population city is "+v.stream().mapToInt(c->c.getPopultion()).max().orElse(0));
                                });
       
       /*
           Highest population capital
       */
       int max = cities.stream().filter(c->c.isCapital())
               .mapToInt(p->p.getPopultion())
               .max()
               .orElse(0);
       System.out.println("Highest population capital is "+ max);
    }
}
