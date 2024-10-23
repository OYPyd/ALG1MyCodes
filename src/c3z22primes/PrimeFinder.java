/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c3z22primes;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class PrimeFinder {
    public static boolean primeFinder(int num){
        boolean isPrime = true;
        int highestNumToCheck = (int)Math.ceil(Math.sqrt(num));
        int i = 2;
        while (i<= highestNumToCheck){
            if(num % i == 0){
                isPrime = false;
                break;
            }
            i++;
        }
        if(num < 2){
            isPrime = false;
        }
        return isPrime;
    }
    
    public static void print(boolean b, int num){
        if(b){
            System.out.print(num);
            System.out.println(" is True.");
        } else {
            System.out.print(num);
            System.out.println(" is False.");
        }
    }
    
    public static void print(boolean b){
        if(b){
            System.out.println("is True.");
        } else {
            System.out.println("is False.");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.print("Zadej Cele cislo: ");
        int num = sc.nextInt();
        print(primeFinder(num),num);
        }
    }
}
