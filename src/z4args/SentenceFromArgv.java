/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z4args;

/**
 *
 * @author vojta
 */
public class SentenceFromArgv {
    public static void sentanceArgs(String[] args){
        final int year = Integer.parseInt(args[0]);
        final String name = args[1];
        final char class_name = args[2].charAt(0);
        final int cur_year = 2024;
        
        int class_num = cur_year - year - 6;
        int age = cur_year - year;
        String message = "Dite "+name+" ma "+ Integer.toString(age) +" let a chodi do "+ Integer.toString(class_num) +"."+class_name+".";
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        sentanceArgs(args);
    }
}
