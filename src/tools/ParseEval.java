/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author vojta
 */
public class ParseEval {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Eval: ");
        String line = sc.nextLine();
        System.out.println(eval("22+"+line));
    }
    
    public static int eval(String a){
        int num = 0;
        int num1 = 0;
        char cur = ' ';
        for (int i = 0; i < a.length(); i++){
            switch(a.charAt(i)){
            case '+': case '-': case '*': case '/':
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
                break;}
        return num;
    }
}
