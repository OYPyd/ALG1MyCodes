/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c3z3pointscircle;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class PointCircle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("A(x): ");
        double x = Double.parseDouble(sc.nextLine());
        System.out.print("A(y): ");
        double y = Double.parseDouble(sc.nextLine());
        System.out.print("S(x): ");
        double sx = Double.parseDouble(sc.nextLine());
        System.out.print("S(y): ");
        double sy = Double.parseDouble(sc.nextLine());
        System.out.print("r: ");
        double r = Double.parseDouble(sc.nextLine());
        double lenSAx = Math.abs(x-sx);
        double lenSAy = Math.abs(y-sy);
        double lenSA = Math.sqrt(lenSAx*lenSAx+lenSAy*lenSAy);
        if(lenSA > r){
            System.out.println("A je mimo");
        }else if (lenSA < r){
            System.out.println("A je v");
        }else{
            System.out.println("A leží na");
        }
    }
}