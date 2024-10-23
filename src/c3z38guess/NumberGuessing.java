/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c3z38guess;

import java.util.Random;

/**
 *
 * @author vojta
 */
public class NumberGuessing {
    int num;
    
    public NumberGuessing(int high_bound){
        Random rand = new Random();
        num = rand.nextInt(high_bound);
    }
    
    public NumberGuessing(int low_bound, int high_bound){
        Random rand = new Random();
        num = low_bound + rand.nextInt(high_bound - low_bound);
    }
    
    public boolean guess(int num){
        if (num > this.num){
            System.out.println("cislo mensi");
        }else if (num < this.num){
            System.out.println("cislo vetsi");
        }else{   
            System.out.println("cislo stejne");
            return true;
        }
        return false;
    }
}
