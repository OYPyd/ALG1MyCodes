/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author vojta
 */
public class DateTimeTools {
    public static void main(String[] args) {
        
    }
    
    public static boolean isLeapYear(int year){
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0; // Only true if divisible by 400
            } else {
                return true; // Divisible by 4 but not by 100
            }
        } else {
            return false; // Not divisible by 4
        }
    }
    
    public static boolean isValidDate(int day, int month, int year) {
        int[] num_of_dates = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(isLeapYear(year)){num_of_dates[1] = 29;}
        return (day <= num_of_dates[month-1]);
    }
    
    public static boolean isValidTime(int h, int min, int secs) {
        return (h >= 0 && h < 24 && min >= 0 && min < 60 && secs >= 0 && secs < 60);
    }
    
    
}
