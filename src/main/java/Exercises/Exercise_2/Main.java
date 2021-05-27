/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
       
        
       
        Map<String, List<City>> map = new HashMap();
        countries.forEach(country -> {
            map.put(country.getName(),country.getCities());
        }); 
        
        map.forEach((k, v) -> 
            System.out.println("Country Name =" + k + ", Cities with sorted =" + v));
    }
    
}
