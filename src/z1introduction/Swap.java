/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package z1introduction;

/**
 *
 * @author vojta
 */
public class Swap {
    
    public static void swap_a(){
        int a = 34;
        int b = 89;
        System.out.print(b);
        System.out.print(", ");
        System.out.println(a);        
    }
    
    public static void swap_b(){
        int a = 34;
        int b = 89;
        int temp = a;
        a = b;
        b = temp;
        System.out.print(a);
        System.out.print(", ");
        System.out.println(b);
    }    
    
    public static void swap_c(){
        int a = 34;
        int b = 89;
        a = a + b;
        b = a - b;
        a = a - b;        
        System.out.print(a);
        System.out.print(", ");
        System.out.println(b);
    }
    
    public static void main(String[] args) {
        swap_a();
        swap_b();
        swap_c();
    }
}
