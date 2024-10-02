/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercises;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class ToSeconds {
    public static void toSeconds_a(){
        int h = 1;
        int min = 2;
        int secs = 3;
        //
        secs = secs + min*60 + h*3600;
        //
        System.out.println(secs);
    }
    public static void toSeconds_b(String[] args){
        int h = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int secs = Integer.parseInt(args[2]);
        //
        secs = secs + min*60 + h*3600;
        //
        System.out.println(secs);
    } 
    public static void toSeconds_c(){
        Scanner sc = new Scanner(System.in);
        int h = Integer.parseInt(sc.nextLine());
        int min = Integer.parseInt(sc.nextLine());
        int secs = Integer.parseInt(sc.nextLine());
        //
        secs = secs + min*60 + h*3600;
        //
        System.out.println(secs);
    }
    public static void main(String[] args) {
        toSeconds_a();
    }
}
