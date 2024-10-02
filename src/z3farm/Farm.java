/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z3farm;

/**
 *
 * @author vojta
 */
public class Farm {
    public static void farm(){
        final int pocetHus = 5;
        final int pocetKraliku = 4;
        int pocetZvirat = pocetHus + pocetKraliku;
        int pocetNohou = pocetHus*2 + pocetKraliku*4;
        
        System.out.println("Pocet hus: "+ pocetHus);
        System.out.println("Pocet kraliku: "+ pocetKraliku);
        System.out.println("Na farme je "+pocetZvirat+" zvirat a maji "+pocetNohou+" nohou.");
    }
    
    public static void main(String[] args) {
        farm();
    }
}
