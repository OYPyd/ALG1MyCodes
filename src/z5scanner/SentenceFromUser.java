/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z5scanner;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class SentenceFromUser {
    public static void sentanceUser(){
        Scanner sc = new Scanner(System.in);
        int year = Integer.parseInt(sc.nextLine());
        String name = sc.nextLine();
        char class_name = sc.nextLine().charAt(0);
        final int cur_year = 2024;
        
        int class_num = cur_year - year - 6;
        int age = cur_year - year;
        String message = "Dite "+name+" ma "+ Integer.toString(age) +" let a chodi do "+ Integer.toString(class_num) +"."+class_name+".";
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        sentanceUser();
    }
}
