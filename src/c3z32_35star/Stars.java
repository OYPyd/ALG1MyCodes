/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c3z32_35star;

/**
 *
 * @author vojta
 */
public class Stars {
    public static void z32(int num){
        for (int i = 0; i < num-1; i++){
            System.out.print("* ");
        }
        System.out.println("*");
    }
    
    public static void z33(int num){
        for (int j = 0; j < num; j++){
        for (int i = 0; i < num-1; i++){
            System.out.print("* ");
        }
        System.out.println("*");}
    }
    
    public static void z34(int num){
        for (int j = 0; j < num; j++){
        for (int i = num-j; i < num; i++){
            System.out.print("* ");
        }
        System.out.println("*");}
    }
    
    public static void z35(int num){
        for (int j = 0; j < num; j++){
        for (int k = 0; k < num-j; k++){
            System.out.print(" ");
        }
        for (int i = num-j; i < num; i++){
            System.out.print("* ");
        }
        System.out.println("*");}
    }
    
        public static void z36(int num){
        for (int j = 0; j < num; j++){
        for (int k = 0; k < num-j; k++){
            System.out.print(" ");
        }
        for (int i = 1; i < num; i++){
            System.out.print("* ");
        }
        System.out.println("*");}
    }
    
    public static void main(String[] args) {
        z36(15);
    }
}
