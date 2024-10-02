/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z2sentence;

/**
 *
 * @author vojta
 */
public class Sentance {
    public static void sentance(){
        final int year = 2010;
        final String name = "Jon";
        final char class_name = 'A';
        final int cur_year = 2024;
        
        int class_num = cur_year - year - 6;
        int age = cur_year - year;
        String message = "Dite "+name+" ma "+ Integer.toString(age) +" let a chodi do "+ Integer.toString(class_num) +"."+class_name+".";
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        sentance();
    }
}
