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
    
    public static int eval(String a){
        int num = 0;
        int num1 = 0;
        char cur = ' ';
        for (int i = 0; i < a.length; i++){
            switch(a.charAt(i)){
            case '+':
            case '-':
            case '*':
            case '/':
                switch(cur){
                    case ' ':
                        num = num1;
                        break;
                    case '+':
                        num = num + num1;
                        break;
                    case '-':
                        num = num - num1;
                        break;
                    case '*':
                        num = num * num1;
                        break;
                    case '/':
                        num = num / num1;
                        break;
                }
                num1 = 0;
                cur = a.charAt(i);
            break;
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                num1 = num1*10 + Character.getNumericValue(a.charAt(i));
            }
        }
        return num;
    }
}
