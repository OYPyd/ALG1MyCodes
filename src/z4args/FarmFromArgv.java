/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z4args;


/**
 *
 * @author vojta
 */
public class FarmFromArgv {
   public static void farmArgs(String[] args){
        final int pocetHus = Integer.parseInt(args[0]);
        final int pocetKraliku = Integer.parseInt(args[1]);
        int pocetZvirat = pocetHus + pocetKraliku;
        int pocetNohou = pocetHus*2 + pocetKraliku*4;
        
        System.out.println("Pocet hus: "+ pocetHus);
        System.out.println("Pocet kraliku: "+ pocetKraliku);
        System.out.println("Na farme je "+pocetZvirat+" zvirat a maji "+pocetNohou+" nohou.");
    }
    
    public static void main(String[] args) {
        farmArgs(args);
    } 
}
