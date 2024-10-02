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
public class FarmFromUser {
    public static void farmUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Pocet hus: ");
        int pocetHus = Integer.parseInt(sc.nextLine());
        System.out.print("Pocet kraliku: ");
        int pocetKraliku = Integer.parseInt(sc.nextLine());
        int pocetZvirat = pocetHus + pocetKraliku;
        int pocetNohou = pocetHus*2 + pocetKraliku*4;
        
        
        
        System.out.println("Na farme je "+pocetZvirat+" zvirat a maji "+pocetNohou+" nohou.");
    }
    
    public static void main(String[] args) {
        farmUser();
    } 
}
