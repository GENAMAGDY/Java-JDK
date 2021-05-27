/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercises.Exercise_3;

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
        String string1 = "BetterString";
        String string2 = "String";
        StringUtils isBetter_Length = (n, d) -> n.length() > d.length();
        if(isBetter_Length.betterString(string1, string2)) System.out.println(string1+" is better than "+string2); 
        else System.out.println(string2+" is better than "+string1); 
        
        /*
           Given a String, the task is to check whether a string contains only alphabets or not.
        */
        
        Alphabet isAlphabet_ = (s)-> s.chars().allMatch(Character::isLetter);
        
        System.out.println("Test of string contains only alphabets");
        System.out.println("First Test Case Input= 'BetterString' , Output: " + isAlphabet_.isAlphabet("BetterString"));
        System.out.println("First Test Case Input= 'Better023String' , Output: " + isAlphabet_.isAlphabet("Better023String"));
        
    }
    
}
