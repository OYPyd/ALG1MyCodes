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
    public static void Widmark(double volumeOfDrink, double alcoholComparedToVolume, double mass, boolean sex){
        final float RHO = 0.8f; //
        final float R; //
        final float BETA; //
        if(sex){
        R = 0.7f;
        BETA = 0.1f;
        } else{
        R = 0.8f;
        BETA = 0.085f;
        }
        
        double massEthanol = volumeOfDrink*alcoholComparedToVolume*RHO;
        double promilesOfAlcohol = massEthanol/mass/R;
        double speed = mass*BETA;
        
        
    }
    
    public static void main(String[] args) {
        
    }
}
