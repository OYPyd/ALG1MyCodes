/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c_special;

import java.util.Scanner;
/**
 *
 * @author vojta
 */
public class Snowman {
    public static void main(String[] args) {
        int radius = 4; // Adjust the radius as desired
        int balls = 4;
        int buttons = 2;
        Scanner sc = new Scanner(System.in);
        try{
        System.out.println("Max polomer koule: ");
        radius = sc.nextInt();
        }catch(Exception e){}
        try{
        System.out.println("Počet koulí: ");
        balls = sc.nextInt();
        }catch(Exception e){}
        try{
        System.out.println("Min počet knoflíků: ");
        buttons = sc.nextInt();
        }catch(Exception e){}
        System.out.println("");
        SnowmanGenerate(radius, balls, buttons);
    }
    
    public static void SnowmanGenerate(int radius_max, int amount_of_balls, int buttons_min){
        System.out.print(generateHead(radius_max-amount_of_balls+2,amount_of_balls-2));
        boolean first = true;
        for(int i = 1; i < amount_of_balls; i++){
            System.out.print(generateCircle(radius_max-amount_of_balls+1+i,buttons_min+i-1,amount_of_balls-1-i, first));
            first = false;
        }
    }

    public static String generateHead(int radius, int offset){
        StringBuilder circle = new StringBuilder();
        StringBuilder offset_sb = new StringBuilder();
        for (int i = 0; i < offset; i++){
            offset_sb.append("  ");
        }
        String offset_str = offset_sb.toString();
        double threshold = 0.5;
        circle.append(offset_str);
            for(int i = 0; i < 2*radius+1; i++){
                if(i >= radius/2 && i <= radius + radius/2){
                    circle.append("--");
                } else {
                    circle.append("  ");
                }
            }
            circle.append("\n");
        for (int y = 1; y <= radius/4; y++){
            circle.append(offset_str);
            for(int i = 0; i < 2*radius+1; i++){
                if(i == radius/2 || i == radius + radius/2){
                    circle.append("||");
                } else {
                    circle.append("  ");
                }
            }
            circle.append("\n");
        }
        for (int y = radius; y >= -radius+1; y--) {
            circle.append(offset_str);
            for (int x = -radius; x <= radius; x++) {
                double distance = Math.sqrt(x * x + y * y);
                
                if((distance - radius) > threshold && y>0 && (x == radius/2 || x == -radius/2)){
                    circle.append("||");
                }else if(y == 1 &&(x == 1 || x == -1)){
                circle.append("0 ");
                }
                else if(y == -1 && x >= 0 && x < radius/2){
                circle.append("==");
                }
                else if(y == -1 && x == radius/2){
                circle.append("=>");
                }
                else if (Math.abs(distance - radius) < threshold) {
                    circle.append("**");
                } else {
                    circle.append("  ");
                }
            }
            circle.append("\n");
        }
        return circle.toString();
    }
    
    public static String generateCircle(int radius, int buttonCount, int offset, boolean isTop) {
        StringBuilder circle = new StringBuilder();
        StringBuilder offset_sb = new StringBuilder();
        for (int i = 0; i < offset; i++){
            offset_sb.append("  ");
        }
        String offset_str = offset_sb.toString();
        double threshold = 0.5; 
        int used = 0;
        int buttonSpacing = (2 * radius) / (buttonCount + 1);
        
        boolean isScarf = false;
        int ScarfPos = -3*radius;
        if(isTop == true){
            circle.append(offset_str);
            for (int x = -radius; x <= radius; x++) {
                double distance = Math.sqrt(x * x + radius * radius);

                if (Math.abs(distance - radius) < threshold) {
                    circle.append("==");
                    isScarf = true;
                } else {
                    circle.append("  ");
                    if(isScarf){
                        ScarfPos = x;
                        isScarf = false;
                    }
                }
            }
            circle.append("\n");
        }
        for (int y = radius-1; y >= -radius; y--) {
            if(isTop == false && y == radius){y--;}
            circle.append(offset_str);
            for (int x = -radius; x <= radius; x++) {
                double distance = Math.sqrt(x * x + y * y);

                if (y == radius-1 && isTop && x == (ScarfPos)){
                    for(int i = 0; i < radius; i++){
                        circle.append("==");
                    }
                    x = 3*radius;
                } else if (Math.abs(distance - radius) < threshold) {
                    circle.append("**");
                } else if (distance < radius && x == 0 && (y) % buttonSpacing == 0 && used < buttonCount) {
                    used += 1;
                    circle.append("O ");
                } else {
                    circle.append("  ");
                }
            }
            circle.append("\n");
        }
        return circle.toString();
    }
}