/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c_special;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snowman_2D extends JFrame {

    public Snowman_2D() {
        
        initUI(); 
    }
    
    private void initUI() {
        
        add(new Board()); //creates the instance of snake
               
        setResizable(false); //doesn't allow for the window to be Resizable
        pack(); //sets the window dimensions to fit perfectly
        
        setTitle("Snowman"); //sets the title of the window
        setLocationRelativeTo(null); //places the window in the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends JFrame on colsing the window
    } 
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> { //waits until the first window is closed to create a new window
            JFrame ex = new Snowman_2D();
            ex.setVisible(true);
        });
    } 
} 