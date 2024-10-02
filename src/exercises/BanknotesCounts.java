/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercises;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class BanknotesCounts {
    public static void banknotesCounts_a(){
        int value = 443;
        final int[] denominations = {100,50,20,10,5,2,1};
        final int[] count = {0,0,0,0,0,0,0};
        //
        for (int i = 0; i<denominations.length; i++){
            count[i] =  value/denominations[i];
            value = value%denominations[i];
        }
        //
        /*for (int i = 0; i<count.length; i++){
            System.out.print(count[i]);
            System.out.print(" ");
        }*/
        for (int i = 0; i<count.length; i++){
            for (int j =0; j<count[i]; j++){
                System.out.print(denominations[i]);
                System.out.print(" ");
            }
        }
    }
    public static void banknotesCounts_b(String[] args){
        int value = Integer.parseInt(args[0]);
        final int[] denominations = {100,50,20,10,5,2,1};
        final int[] count = {0,0,0,0,0,0,0};
        //
        for (int i = 0; i<denominations.length; i++){
            count[i] =  value/denominations[i];
            value = value%denominations[i];
        }
        //
        /*for (int i = 0; i<count.length; i++){
            System.out.print(count[i]);
            System.out.print(" ");
        }*/
        for (int i = 0; i<count.length; i++){
            for (int j =0; j<count[i]; j++){
                System.out.print(denominations[i]);
                System.out.print(" ");
            }
        }
    }
    public static void banknotesCounts_c(){
        Scanner sc = new Scanner(System.in);
        int value = Integer.parseInt(sc.nextLine());
        final int[] denominations = {100,50,20,10,5,2,1};
        final int[] count = {0,0,0,0,0,0,0};
        //
        for (int i = 0; i<denominations.length; i++){
            count[i] =  value/denominations[i];
            value = value%denominations[i];
        }
        //
        /*for (int i = 0; i<count.length; i++){
            System.out.print(count[i]);
            System.out.print(" ");
        }*/
        for (int i = 0; i<count.length; i++){
            for (int j =0; j<count[i]; j++){
                System.out.print(denominations[i]);
                System.out.print(" ");
            }
        }
    }
    
    public static void main(String[] args) {
        banknotesCounts_a();        
    }
}
