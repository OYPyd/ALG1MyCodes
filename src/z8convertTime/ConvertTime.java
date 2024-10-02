/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z8convertTime;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class ConvertTime {
    public static double toHours(int hours, int mins, double secs){
        return hours + (double)mins/60 + secs/3600;
    }
    
    public static double[] fromHours(double hours){
        double[] result = new double[3];
        result[0] = (double)(int)hours;
        result[1] = (double)(int)((hours*60)%60);
        result[2] = (double)(int)((hours*3600)%60);
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hours = Integer.parseInt(sc.nextLine());
        int mins = Integer.parseInt(sc.nextLine());
        double secs = Double.parseDouble(sc.nextLine());
        System.out.println("= "+toHours(hours,mins,secs)+"h");
        double hours2 = Double.parseDouble(sc.nextLine());
        double[] time2 = fromHours(hours2);
        System.out.println("= "+(int)time2[0]+"h "+(int)time2[1]+"min "+time2[2]+"s");
    }
}
