/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z7temperature;

import java.util.Scanner;
/**
 *
 * @author vojta
 */
public class TemperatureAverage {
    public static double average(double eight, double twelve, double eightteen){
        return (eight + twelve + eightteen*2)/4;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double eight = Double.parseDouble(sc.nextLine());
        double twelve = Double.parseDouble(sc.nextLine());
        double eightteen = Double.parseDouble(sc.nextLine());
        System.out.println("average temperature: "+average(eight,twelve,eightteen));
    }
}
