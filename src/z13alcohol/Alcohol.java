/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z13alcohol;

/**
 *
 * @author vojta
 */
public class Alcohol {
    /**
     * volumeOFDrink - dm^3 alias 1/10^3 m^3
     * alcoholComparedToVolum - 0..1
     * mass - kg
     * sex - male: true, female: false
     */
    public static void Widmark(double volumeOfDrink, double alcoholComparedToVolume, double mass, boolean sex){
        final float RHO = 0.8f; //  g/cm^3 .. 10^3 kg/m^3
        final float R; // 0..1
        final float BETA; // g/h
        if(sex){
        R = 0.7f;
        BETA = 0.1f;
        } else{
        R = 0.8f;
        BETA = 0.085f;
        }
        
        double massEthanol = volumeOfDrink*alcoholComparedToVolume*RHO; // 1000/1000 .. kg
        double promilesOfAlcohol = massEthanol/mass/R; // 0..1
        double speed = mass*BETA; // kg * g/h
        
        
    }
    
    public static void main(String[] args) {
        
    }
}
