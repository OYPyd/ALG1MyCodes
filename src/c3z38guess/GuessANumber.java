/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c3z38guess;

import java.util.Scanner;

/**
 *
 * @author vojta
 */


public class GuessANumber {      
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       System.out.println("Upperlimit: ");
       NumberGuessing tool = new NumberGuessing(sc.nextInt()); 
       boolean isDone = false;
       while (!isDone){           
           isDone = tool.guess(sc.nextInt());
       }
    }
}
