/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author vojta
 */
public class VectorTools {
    public static void main(String[] args) {
        
    }
    
    public static <T extends Number> float getLenght(T[] array) {
        float sum = 0.0f;
        for (T number : array) {
            sum += number.floatValue() * number.floatValue();
        }
        return (float)Math.sqrt(sum);
    }
    
    public static <T extends Number> float angle(T[] a, T[] b) {
        float sum = 0.0f;
        float norma = 0.0f;
        float normb = 0.0f;
        if(a.length != b.length){throw new ArithmeticException("Vector mismatch");}
        for (int i = 0; i < a.length; i++) {
            sum += a[i].floatValue() * b[i].floatValue();
            norma += a[i].floatValue() * a[i].floatValue();
            normb += b[i].floatValue() + b[i].floatValue();
        }
        return (float)Math.acos(sum/(float)Math.sqrt(norma)/(float)Math.sqrt(normb));
    }
    
    public static <T extends Number> float dotProduct(T[] a, T[] b) {
        float sum = 0.0f;
        if(a.length != b.length){throw new ArithmeticException("Vector mismatch");}
        for (int i = 0; i < a.length; i++) {
            sum += a[i].floatValue() * b[i].floatValue();
        }
        return sum;
    }
    
    public static <T extends Number> boolean perpendicularTest(T[] a, T[] b){
        float dot = dotProduct(a,b);
        return (dot == 0);
    }
}
