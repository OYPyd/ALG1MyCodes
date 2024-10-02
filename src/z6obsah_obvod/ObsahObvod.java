/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z6obsah_obvod;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class ObsahObvod {
    public ObsahObvod(){}
    
    public static double volumeOfCircle(double radius){
        return radius*radius*Math.PI;
    }
    
    public static double circumferenceOfCircle(double radius){
        return 2*Math.PI*radius;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double radius = Double.parseDouble(sc.nextLine());
        System.out.println("S = "+volumeOfCircle(radius));
        System.out.println("o = "+circumferenceOfCircle(radius));
    } 
}
