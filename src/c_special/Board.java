/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c_special;

/**
 *
 * @author vojta
 */


import java.awt.Color; //libraries
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 800; //size of the screen
    private final int B_HEIGHT = 800;
    private final int A_HEIGHT = 64; //additional height
    private final int DOT = 32; //size of the chunk of the snake
    private final int GAP = 160; //distance between the score text
    private final int ALL_DOTS = B_WIDTH / DOT * B_HEIGHT / DOT ; //number of possible chunks
    private final int RAND_POS = 22; //random multiplier
    private final int START_POS = 288; //starting position
    private final int DELAY = 15; //default speed of repainting

    private final int x[] = new int[ALL_DOTS]; //initialization of the coordinates of the snake
    private final int y[] = new int[ALL_DOTS];

    private final int x_w[] = new int[27]; //initialization of the coordinates of the main water stream
    private final int y_w[] = new int[27];

    private final int x_d[] = new int[15]; //initialization of the coordinates of the walls
    private final int y_d[] = new int[15];
    private final int z_d[] = new int[15]; //time left to appear

    private int screen = 0; //controls the currently used screen (0 - main game, 1 - main menu, 2 - game over screen)

    private int dx = 0; //assits the creation of the water stream coordinates
    private int dy = 0;

    private int time; //tracks the amount of game time that has passed
    private int time2; //used for slowing the snake
    private int delay_time; //delay of the speed of repainting

    //private int Debug; //tracks debug data
    private boolean canDie = true; //for debuging purposes

    private int music = 0; //used for switching between music and alt music
    private boolean logicalTrack = false; //says if the music track has a logical continuation
    private boolean firstwater = false; //debuging mechanism for the water streams

    private int cooltime; //countdown of the double speed
    private int cooldown; //stores the data of the last change in phase
    private int coolwater; //stores the data of the last activated water stream
    private int water_duration = 0; //extends the duration of the water stream
    //private int inBubbletime;

    private int score; //stores the score of the player
    private int hsscore = 0; //stores the highest score of the session
    //private int highscore = 0; //UNUSED stores the value of the highest score
    private int multiplier = 1; //score multiplier - how much should be the recieved score multiplied

    private int dots; //number of current snake chunks
    private int x_a1 = -DOT; //positions of the pear
    private int y_a1 = -DOT;
    private int z_a1 = 5;    //time left to appear
    private int x_a2 = -DOT; //positions of the apple
    private int y_a2 = -DOT;
    private int z_a2 = 5;    //time left to appear
    private int x_a3 = -DOT;
    private int y_a3 = -DOT; //positions of the lightning bolt
    private int z_a3 = 5;    //time left to appear
    private int waterSide;   //number which helps with randomization of the side form the water appears
    private int x_water = -DOT;  //positions of the water stream
    private int y_water = -DOT;
    private int z_water;         //time left to appear
    private int x_bubble1 = -DOT;//positions of the bubbles
    private int y_bubble1 = -DOT;
    private int z_bubble = 0;    //time left to appear
    private int x_bubble2 = -DOT; 
    private int y_bubble2 = -DOT;
    private int x_bomb = -DOT;   //positions of the bombs
    private int y_bomb = -DOT;
    private int z_bomb;          //time left to appear
    private int phase = 0; //keeps track of current phase number
    private int x_off = 0; //positions of the backround offset
    private int y_off = 0;


    private int randomOld; //keeps track of the last played music track
    private int randomOld2; //keeps track of the last played alt music track

    private int direction = 6; //direction where will the snake move: 8-up, 6-right, 4-left, 2-down (like numpad)
    private int directionW = 6; //direction where will the water stream move: 8-up, 6-right, 4-left, 2-down (like numpad)
    private int directionB = 5; //direction where will the bomb move: 8-up, 6-right, 4-left, 2-down, 5-stationary (like numpad)
    private boolean directionOld = false; //stores if the direction has changed
    private boolean inGame = true; //if the game is running
    private boolean inWater = false; //if the snake has touched the water stream
    
    private boolean inWaterBeen = false; //if the snake has been in water
    private boolean inBubble = false; //if the snake carries a bubble

    private boolean stop = true; //prevents further spread of the water stream beyoung the borders

    private Timer timer; //timer wich periodically calls a actionEvent depending on the delay

    private Image ball0; //prepares images for loading
    private Image ball1;
    private Image ball2;
    private Image ball3;

    private Image apple;
    private Image apple_b;
    private Image apple2;

    private Image apple2F0; //frames of the apple
    private Image apple2F1;
    private Image apple2F2;


    private Image background;
    private Image used_head; 
    private Image head[] = new Image[3];
    private Image head2[] = new Image[12];
    private Image speed[] = new Image[6]; //frames of lightning bolt
    private Image water;
    private Image bubble;

    private Image bomb;
    private Image wall[] = new Image[25]; //frames of the wall

    private Image spawn;
    private Image spawn_water;
    private Image explosion[] = new Image[6]; //frames of explosion

	private File audio; //the File which is currently going to be played
    private File audio2; //the File which is the continuation of the previous track

    private Clip clip = null;

    File s_eat01 = new File("src/resources/sound/eat-" + String.valueOf(1) + ".wav"); //defining the source of the music tracks
    File s_eat02 = new File("src/resources/sound/eat-" + String.valueOf(2) + ".wav");
    File s_eat03 = new File("src/resources/sound/eat-" + String.valueOf(3) + ".wav");
    File s_eat04 = new File("src/resources/sound/eat-" + String.valueOf(4) + ".wav");

    File s_explo = new File("src/resources/sound/explosion.wav");
    File s_water_spawn = new File("src/resources/sound/water_spawn.wav");


    File main01 = new File("src/resources/sound/Snake-01.wav");
    File main02 = new File("src/resources/sound/Snake-02.wav");
    File main03 = new File("src/resources/sound/Snake-03.wav");
    File main04 = new File("src/resources/sound/Snake-04.wav");

    File alt01 = new File("src/resources/sound/AltSnake-01.wav");
    File alt02 = new File("src/resources/sound/AltSnake-02.wav");
    File alt03 = new File("src/resources/sound/AltSnake-03.wav");
    File alt04 = new File("src/resources/sound/AltSnake-04.wav");

    File water01 = new File("src/resources/sound/WSnake.wav");
    File bomb01 = new File("src/resources/sound/BSnake.wav");


    public Board() { //main class
        
        for (int z = 0; z < ALL_DOTS; z++) { //default position for the snake chunks
            x[z] = -DOT;
            y[z] = -DOT;
        }
        initBoard(); //initialization of the board
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter()); //allows for input events
        setBackground(Color.black); //sets the background
        setFocusable(true); //sets that this class recieves input

        //playSound();

        clip = playTrack(main01); //plays the first music track

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT + A_HEIGHT)); //applies size of the window
        loadImages(); //loads images

        initGame(); //last setup before the game
        inGame = false; //-----
        screen = 1; //the screen is set to main menu screen
    }

    private Clip playTrack(File x) {
        /* 
        zjistit a popsat
        */
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(x);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return clip;
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
            return null;
        }
    }

    private void reinitBoard() {

        screen = 0;
        setBackground(Color.black);
        dots = 3;
        
        for (int z = 0; z < ALL_DOTS; z++) { //default position for the snake chunks
            x[z] = -DOT;
            y[z] = -DOT;
        }

        for (int z = 0; z < 27; z++) { //default position for the WATER chunks
            x_w[z] = - 20*DOT;
            y_w[z] = - 20*DOT;
        }

        for (int z = 0; z < 15; z++) { //default position for the WALLS
            x_d[z] = -5*DOT;
            y_d[z] = -5*DOT;
        }

        for (int z = 0; z < dots; z++) {
            x[z] = START_POS - z * DOT;
            y[z] = START_POS;
        }

        x_off = 0; //positions of the backround offset
        y_off = 0;
        stop = true;
        phase = 0;
        firstwater = false;

        timer.start();
        inGame = true;
        score = 0;
        cooltime = 1;
        water_duration = 0;
        direction = 6;

        locateApple();
        x_a2 = -DOT;
        y_a2 = -DOT;
        x_bomb = -DOT;
        y_bomb = -DOT;
        x_a3 = -DOT;
        y_a3= -DOT;
        x_bubble1 = -DOT;//default positions of the bubbles
        y_bubble1 = -DOT;
        z_bubble = 0;    //time left to appear
        x_bubble2 = -DOT; 
        y_bubble2 = -DOT;
        inWater = false;
        inWaterBeen = false;
        inBubble = false;
    }

    private void loadImages() {
        //initiates the images
        ImageIcon iibk = new ImageIcon("src/resources/backround2.png");
        background = iibk.getImage();

        //Color c=new Color(0f,0f,0f,.5f); //UNUSED - could possibly recolor objects

        ImageIcon iih[] = new ImageIcon[3]; //iniciation of the wall sprites
        ImageIcon iih2[] = new ImageIcon[12]; //iniciation of the wall sprites
        for (int i = 0; i < 3; i++){
        iih[i] = new ImageIcon("src/resources/head_" + String.valueOf(i) + ".png");
        head[i] = iih[i].getImage();    
        }
        for (int i = 0; i < 6; i++){
            iih2[i] = new ImageIcon("src/resources/head_0" + String.valueOf(i) + ".png");
            head2[i] = iih2[i].getImage();    
            }
        for (int i = 6; i < 12; i++){
                iih2[i] = new ImageIcon("src/resources/head_00.png");
                head2[i] = iih2[i].getImage();    
                }

        ImageIcon iid0 = new ImageIcon("src/resources/dot_0.png");
        ball0 = iid0.getImage();
        ImageIcon iid1 = new ImageIcon("src/resources/dot_2.png");
        ball1 = iid1.getImage();
        ImageIcon iid2 = new ImageIcon("src/resources/dot_1.png");
        ball2 = iid2.getImage();
        ImageIcon iid3 = new ImageIcon("src/resources/dot_3.png");
        ball3 = iid3.getImage();

        ImageIcon iisp = new ImageIcon("src/resources/spawn.png");
        spawn = iisp.getImage();
        ImageIcon iispw = new ImageIcon("src/resources/spawn_water.png");
        spawn_water = iispw.getImage();

        ImageIcon iiex[] = new ImageIcon[6]; //iniciation of the explosion frames
        for (int i = 0; i < 6; i++){
        iiex[i] = new ImageIcon("src/resources/explosion" + String.valueOf(i) + ".png");            
        explosion[i] = iiex[i].getImage();
        }

        ImageIcon iia = new ImageIcon("src/resources/apple2.png");
        apple = iia.getImage();

        ImageIcon iiab = new ImageIcon("src/resources/apple_backround.png");
        apple_b = iiab.getImage();

        ImageIcon iia2 = new ImageIcon("src/resources/head2.png");
        apple2 = iia2.getImage();

        ImageIcon iiapple0 = new ImageIcon("src/resources/apple_0.png");
        apple2F0 = iiapple0.getImage();
        ImageIcon iiapple1 = new ImageIcon("src/resources/apple_1.png");
        apple2F1 = iiapple1.getImage();
        ImageIcon iiapple2 = new ImageIcon("src/resources/apple_2.png");
        apple2F2 = iiapple2.getImage();


        ImageIcon iis[] = new ImageIcon[6]; //iniciation of the lightning bolt sprites
        for (int i = 0; i < 6; i++){
        iis[i] = new ImageIcon("src/resources/speed_" + String.valueOf(i) + ".png");            
        speed[i] = iis[i].getImage();
        }
        ImageIcon iiw = new ImageIcon("src/resources/water.png");
        water = iiw.getImage();

        ImageIcon iib = new ImageIcon("src/resources/bubble.png");
        bubble = iib.getImage();

        ImageIcon iibm = new ImageIcon("src/resources/bomb.png");
        bomb = iibm.getImage();

        ImageIcon iiwe[] = new ImageIcon[25]; //iniciation of the wall sprites
        for (int i = 0; i < 25; i++){
        if (i < 10){
        iiwe[i] = new ImageIcon("src/resources/wall_0" + String.valueOf(i) + ".png");
        }else{
        iiwe[i] = new ImageIcon("src/resources/wall_" + String.valueOf(i) + ".png");    
        }
        wall[i] = iiwe[i].getImage();
    }
}

    /*public void loadMusic() {
        File main01 = new File("src/resources/sound/Snake-01.wav");
        File main02 = new File("src/resources/sound/Snake-02.wav");
        File main03 = new File("src/resources/sound/Snake-03.wav");
        File main04 = new File("src/resources/sound/Snake-04.wav");
        File main05 = new File("src/resources/sound/Snake-05.wav");
        File main06 = new File("src/resources/sound/Snake-06.wav");

    }*/

    private void initGame() {

        dots = 3; //default size of the snake,

        for (int z = 0; z < dots; z++) { //determination of the position of the snake chunks
            x[z] = START_POS - z * DOT;
            y[z] = START_POS;
        }

        for (int z = 0; z < 27; z++) { //default position for the water chunks
            x_w[z] = - 20*DOT;
            y_w[z] = - 20*DOT;
        }

        for (int z = 0; z < 15; z++) { //default position for the walls
            x_d[z] = -5*DOT;
            y_d[z] = -5*DOT;
        }
    
        locateApple(); //defines the position of the pear and creates it

        timer = new Timer(DELAY, this); //creating Timer which periodiacally calls actionPerformed once every DELAY ms.
        timer.start(); //starts the Timer
    }

    @Override
    public void paintComponent(Graphics g) { //redefines the paintComponent with the Graphics 2D data which allows drawing of the objects and effects on the screen
        super.paintComponent(g); //uses the code from the library Graphics 2D

        doDrawing(g); //adds the graphics code to be drawn
    }
    
    private void doDrawing(Graphics g) {
        
    switch (screen){
        default:
            g.drawImage(background, -50 + 2*x_off, A_HEIGHT -50 + 2*y_off, B_WIDTH +50 - x_off, B_HEIGHT + A_HEIGHT +50 - y_off, this); //draws backround
            g.drawLine(0, A_HEIGHT, B_WIDTH, A_HEIGHT);//UI line
             
            if (z_a1 < 2){
            g.drawImage(apple, x_a1, y_a1, DOT, DOT, this); //draws pear
                }else{
                if (z_a1 % 3 < 2){ //allows flickering
                g.drawImage(spawn, x_a1, y_a1, DOT, DOT, this); //draws pear indicater   
                }
            }

            if (z_a2 < 2){
                g.drawImage(apple2, x_a2, y_a2, DOT, DOT, this); 
                }else{ g.drawImage(apple_b, x_a2 + z_a2, y_a2 + z_a2, DOT - z_a2, DOT - z_a2, this);
              //draws apple
                    /*}else{
                    if (z_a2 % 3 < 2){ //allows flickering
                    g.drawImage(spawn, x_a2, y_a2, DOT, DOT, this); //draws apple indicater    
                    }*/ 
                }

                

            if (z_a3 < 2){
                g.drawImage(speed[(time/2)%6], x_a3, y_a3, DOT, DOT, this); //draws lightning bolt
                    }else{
                    if (z_a3 % 3 < 2){ //allows flickering
                    g.drawImage(spawn, x_a3, y_a3, DOT, DOT, this); //draws lightning bolt indicater    
                    }
                }
                
            if (z_water < 2){
                g.drawImage(spawn_water, x_water, y_water, DOT, DOT, this); //draws the water indicater
            }else{
                if (z_water % 3 < 2){ //allows flickering
                g.drawImage(spawn_water, x_water, y_water, DOT, DOT, this); //draws the water indicater
                }
            }

            if (z_bomb < 2){
                g.drawImage(bomb, x_bomb, y_bomb, DOT, DOT, this); //draws bomb
                    }else{
                    if (z_bomb % 3 < 2){ //allows flickering
                    g.drawImage(spawn, x_bomb, y_bomb, DOT, DOT, this); //draws bomb indicater  
                    }
                }
                
            for (int z = 0; z < 15; z++){    
            if (z_d[z] < 2){  
                if ((z_d[z] > -7) && (z_d[z] < 0)){
                g.drawImage(explosion[-z_d[z]], x_d[z]-2*x_off, y_d[z]+y_off, 3*DOT, 3*DOT, this); //draws frames of explosion   
                }else{
                    g.drawImage(wall[(time+z*4)%25], x_d[z], y_d[z], 3*DOT, 3*DOT, this); //draws walls
                } 
                    }else{
                    if (z_d[z] % 3 < 2){ //allows flickering
                    g.drawImage(spawn, x_d[z], y_d[z], 3*DOT, 3*DOT, this); //draws indicater of those walls   
                    }
                }
            }

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(used_head, x[z], y[z], DOT, DOT, this); //draws head
                    if (inBubble) {
                        g.drawImage(bubble, x[z], y[z], DOT, DOT, this); //draws bubble if worn 
                    }
                } else {
                    if (z % 4 == 3){
                        g.drawImage(ball3, x[z], y[z], DOT, DOT, this); //draws body textures
                        }
                    if (z % 4 == 0){
                        g.drawImage(ball0, x[z], y[z], DOT, DOT, this);
                        }
                    if (z % 4 == 1){
                        g.drawImage(ball1, x[z], y[z], DOT, DOT, this);
                        }
                    if (z % 4 == 2){
                    g.drawImage(ball2, x[z], y[z], DOT, DOT, this);
                    }
                }
            }
            if (firstwater){
            for (int z = 0; z < 27; z++) {
                g.drawImage(water, x_w[z], y_w[z], DOT, DOT, this); //draws main water stream
                if (z != 0) {
                    if ((directionW == 8) || (directionW == 2)){ //draws the side streams depending on the direction of the water stream
                    g.drawImage(water, x_w[z] + DOT, y_w[z], DOT, DOT, this); 
                    g.drawImage(water, x_w[z] - DOT, y_w[z], DOT, DOT, this);
                    } else {
                    g.drawImage(water, x_w[z] , y_w[z] + DOT, DOT, DOT, this);
                    g.drawImage(water, x_w[z] , y_w[z] - DOT, DOT, DOT, this);
                    }
                }
            }
        }
            if (z_bubble < 2){
                g.drawImage(bubble, x_bubble1, y_bubble1, DOT, DOT, this); //draws bubble 1
                g.drawImage(bubble, x_bubble2, y_bubble2, DOT, DOT, this); //draws bubble 2
                    }else{
                    if (z_bubble % 3 < 2){ //allows flickering
                        g.drawImage(bubble, x_bubble1, y_bubble1, DOT, DOT, this); //draws bubble 1
                        g.drawImage(bubble, x_bubble2, y_bubble2, DOT, DOT, this); //draws bubble 2
                    }
                }
                
            Toolkit.getDefaultToolkit().sync(); //helps syncronization between the reapaints
            score(g); //draws scoreboard
            break;
        case 1:
            mainMenu(g); //draws main menu
            break;
        case 2:
            gameOver(g); //draws game over menu when the game ends
            break;
        }        
    }

    private void mainMenu(Graphics g) {
        
        String title = "Snake"; //inicialition of the texts
        String press = "Press R to start"; 
        Font big = new Font("Lucida Console", Font.BOLD, 96); //defines the font for use
        FontMetrics metr = getFontMetrics(big); //gets the mesuring scale of the font
        Font smaller = new Font("Lucida Console", Font.BOLD, 28); 
        FontMetrics meter = getFontMetrics(smaller);
        g.setColor(Color.white); //sets the color of text
        g.setFont(big); //sets the font
        

        g.drawString(title, (B_WIDTH - metr.stringWidth(title)) / 2, (B_HEIGHT+A_HEIGHT)/2); //draws the text
        g.setFont(smaller); //sets the font
        g.drawString(press, (B_WIDTH - meter.stringWidth(press)) / 2, (B_HEIGHT+A_HEIGHT)/2 + GAP); //draws the text

    }

    private void score(Graphics g) {

        //String msgDebug = String.valueOf(directionW); //inicialition of the texts and converts numbers into the String data type
        
        String msgScore = String.valueOf(score);
        String msgScore2 = "Score";

        String msgMultiplier = "x " + String.valueOf(multiplier);
        String msgMultiplier2 = "Multiplier";

        String msgCooltime = String.valueOf(cooltime);
        String msgCooltime2 = "Cooltime";

        Font small = new Font("Lucida Console", Font.BOLD, 28); //defines the font for use
        FontMetrics metr = getFontMetrics(small); //gets the mesuring scale of the font

        g.setColor(Color.white); //sets the color of text
        g.setFont(small); //sets the font

        g.drawString(msgScore, (B_WIDTH - metr.stringWidth(msgScore)) / 2, DOT); //draws the text
        g.drawString(msgScore2, (B_WIDTH - metr.stringWidth(msgScore2)) / 2, DOT*2 - 5);

        g.drawString(msgMultiplier, (B_WIDTH - metr.stringWidth(msgScore)) / 2 + GAP, DOT);
        g.drawString(msgMultiplier2, (B_WIDTH - metr.stringWidth(msgScore2)) / 2 + GAP - 10 , DOT*2 - 5);

        g.drawString(msgCooltime, (B_WIDTH - metr.stringWidth(msgScore)) / 2 - GAP, DOT);
        g.drawString(msgCooltime2, (B_WIDTH - metr.stringWidth(msgScore2)) / 2 - GAP - 10, DOT*2 - 5);

        //g.drawString(msgDebug, 0, DOT); //used for debuging purposes only - NOT FINAL
    }

    private void checkTime() { /* */
        time++; //counts how much game time has passed
        delay_time++; //counts the additional delay between two game time units

        if (z_a1 > 1){ //updates time left to appear
        z_a1--;
        }
        if (z_bomb > 1){
            z_bomb--;
            }
        if (z_a2 > 1){
        z_a2--;
        }
        if (z_a3 > 1){
        z_a3--;
        }
        for (int z = 0; z < 15; z++) {
            if (z_d[z] > 1){
                z_d[z]--;
            }
            if (z_d[z] < 0){
                z_d[z]--;
                if (z_d[z] < -6){
            x_d[z] = -3*DOT;
            y_d[z] = -3*DOT;
            z_d[z] = 15;
            }
        }}
        if (cooltime > 0){ //resets score multiplier
            if (cooltime == 1){
                multiplier = 1;
            } 
            cooltime--;
        }
        if (time % 20 == 0){ //gives one score for every 20th game time unit
        score++;
        }
        if (time == (coolwater + 50 + water_duration)){ //destroys the water after certain amount of time
            stop = true;
            for (int z = 0; z < 27; z++) { //default position for water chunks
                x_w[z] = - 20*DOT;
                y_w[z] = - 20*DOT;
            }
            x_water=-DOT; //resets the position of the water marker
            y_water=-DOT;
        }

        if (z_water > 1){ //updates time left to appear
            z_water--;
            }
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over"; //inicialition of the texts and converts numbers into the String data type

        String msgScore = String.valueOf(score);
        String msgScore2 = "Score";

        String press = "Press R to start"; //inicialition of the texts

        if (score > hsscore){ //updates the highest score of the session
            hsscore = score;
        }

        String msgHSScore = String.valueOf(hsscore);
        String msgHSScore2 = "Highest Session";
        String msgHSScore3 = "Score";

        //String msgHighscore2 = "Highscore"; 
        //String msgHighscoreM = String.valueOf(highscore); //UNUSED Highscore

        Font small = new Font("Lucida Console", Font.BOLD, 56); //defines the fonts for use
        FontMetrics metr = getFontMetrics(small); //gets the mesuring scales of the fonts

        Font smaller = new Font("Lucida Console", Font.BOLD, 28); 
        FontMetrics meter = getFontMetrics(smaller);

        g.setColor(Color.white); //sets the color of text
        g.setFont(small); //sets the font to use
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2); //draws the text

        g.setFont(smaller); //sets the font to use
        g.drawString(msgScore2, (B_WIDTH - meter.stringWidth(msgScore2)) / 2, B_HEIGHT / 2 + DOT*2 - 5);
        g.setFont(small); //sets the font to use
        g.drawString(msgScore, (B_WIDTH - metr.stringWidth(msgScore)) / 2, B_HEIGHT / 2 + DOT*3 + 10);
        g.setFont(smaller); //sets the font to use

        g.drawString(msgHSScore, (B_WIDTH - meter.stringWidth(msgScore)) / 2 + GAP, B_HEIGHT / 2 + DOT);
        g.drawString(msgHSScore2, (B_WIDTH - meter.stringWidth(msgScore2)) / 2 + GAP, B_HEIGHT / 2 + DOT*2 - 5);
        g.drawString(msgHSScore3, (B_WIDTH - meter.stringWidth(msgScore2)) / 2 + GAP, B_HEIGHT / 2 + DOT*3 - 10);

        g.drawString(press, (B_WIDTH - metr.stringWidth(press)) / 2 + GAP, B_HEIGHT / 2 + DOT*3 + 10 + GAP);



        //g.drawString(msgHighscoreM, (B_WIDTH - meter.stringWidth(msgScore)) / 2 - GAP, B_HEIGHT / 2 + DOT);
        //g.drawString(msgHighscore2, (B_WIDTH - meter.stringWidth(msgScore2)) / 2 - GAP, B_HEIGHT / 2 + DOT*2 - 5);
    }

    private void checkApple() { 

        if ((x[0] == x_a1) && (y[0] == y_a1) && (z_a1 < 2)) { //checks if the snake can eat pear

            playTrack(s_eat01);
            dots = dots + 5;
            score = score + 35 * multiplier;
            locateApple();
        }
        if ((x[0] == x_a2) && (y[0] == y_a2) && (z_a2 < 2)) { //checks if the snake can eat apple

            if (dots > 5){
            dots = dots - 5;

            for (int z = dots; z < dots + 5; z++) {
                x[z] = -DOT;
                y[z] = -DOT;
            }

            score = score - 10 * multiplier;
            locateApple2();
            playTrack(s_eat02);
            
            } else {
                inGame = false;
            }
        }
        if ((x[0] == x_a3) && (y[0] == y_a3) && (z_a3 < 2)) { //checks if the snake can eat the lightning bolt

            cooltime = 60;
            multiplier = 2;
            locateApple3();
            playTrack(s_eat03);
        }
        if ((x[0] == x_bubble1) && (y[0] == y_bubble1) || (x[0] == x_bubble2) && (y[0] == y_bubble2)) { //checks if the snake can wear a bubble
            
            inBubble = true; //snake successfully wears a bubble
            x_bubble1 = -DOT; //removes bubbles from the screen
            y_bubble1 = -DOT;
            x_bubble2 = -DOT;
            y_bubble2 = -DOT;
        }
    }

    private void checkBomb(){  //checks if the bomb can be bonked from snakes head (and two additional tiles of snake for better control)
        if ((x[0] == x_bomb) && (y[0] == y_bomb) && (z_bomb < 2) || (x[1] == x_bomb) && (y[1] == y_bomb) && (z_bomb < 2) || (x[2] == x_bomb) && (y[2] == y_bomb) && (z_bomb < 2)) {

            directionB = direction; //changes the direction of a bomb depending on the direction of snake

        }
        for (int z = 0; z < 27; z++) { //detecs if the bomb has collided with water and stops the bomb
            if ((x_bomb == x_w[z]) && (y_bomb == y_w[z])){
                directionB = 5;
            }
            if (z != 0) {
                if ((directionW == 8) || (directionW == 2)){ //used for finding the side streams
                    if ((x_bomb == x_w[z] + DOT) && (y_bomb == y_w[z])){
                        directionB = 5;
                    }
                    if ((x_bomb == x_w[z] - DOT) && (y_bomb == y_w[z])){
                        directionB = 5;
                    }
                } else {
                    if ((x_bomb == x_w[z]) && (y_bomb == y_w[z] + DOT)){
                        directionB = 5;
                    }
                    if ((x_bomb == x_w[z]) && (y_bomb == y_w[z] - DOT)){
                        directionB = 5;
                    }
                }
            }
        } 
        for (int z = 0; z < 15; z++) { //detecs if the bomb has collided with a wall and the bomb explodes and destroys the wall
        if ((x_bomb < x_d[z] + 3*DOT) && (x_bomb > x_d[z] -1*DOT) && (y_bomb < y_d[z] + 3*DOT) && (y_bomb > y_d[z] - 1*DOT) && (z_d[z] < 2)){
            z_d[z] = -1;
            playTrack(s_explo);
            score = score + 100 * multiplier;
            locateBomb();
        }}
        for (int z = 0; z < 15; z++) { //detecs if player has collided with a wall and shows game over screen if so
        if ((x[0] < x_d[z] + 3*DOT) && (x[0] > x_d[z] -1*DOT) && (y[0] < y_d[z] + 3*DOT) && (y[0] > y_d[z] - 1*DOT) && (z_d[z] < 2) && (z_d[z] > -1)){
            inGame = false;
            screen = 2;    
        }}
    }


    private void checkWater(){ //detecs if the bomb has collided with water
        inWater = false;
        for (int z = 0; z < 27; z++) {
            if ((x[0] == x_w[z]) && (y[0] == y_w[z])){
                inWater = true;
            }
            if (z != 0) {
                if ((directionW == 8) || (directionW == 2)){ //used for finding the side streams
                    if ((x[0] == x_w[z] + DOT) && (y[0] == y_w[z])){
                        inWater = true;
                    }
                    if ((x[0] == x_w[z] - DOT) && (y[0] == y_w[z])){
                        inWater = true;
                    }
                } else {
                    if ((x[0] == x_w[z]) && (y[0] == y_w[z] + DOT)){
                        inWater = true;
                    }
                    if ((x[0] == x_w[z]) && (y[0] == y_w[z] - DOT)){
                        inWater = true;
                    }
                }
            }
        }
    }

    private void locateApple() { //creation process of the pear

        int r = (int) (Math.random() * RAND_POS + 1);
        x_a1 = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_a1 = ((r * DOT + A_HEIGHT));
        z_a1 = 20;

        for (int z = 0; z < 15; z++) {
        if ((x_a1 < x_d[z] + 3*DOT) && (x_a1 > x_d[z] -1*DOT) && (y_a1 < y_d[z] + 3*DOT) && (y_a1 > y_d[z] - 1*DOT)){
            locateApple();  
        }}
    }

    private void locateBomb() { //creation process of the bomb

        int r = (int) (Math.random() * RAND_POS + 1);
        x_bomb = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_bomb = ((r * DOT + A_HEIGHT));
        z_bomb = 20;
        directionB = 5;

        audio2 = bomb01;
        logicalTrack = true;

    }

    private void locateWall(int i) { //creation process of a wall
        int r = (int) (Math.random() * (RAND_POS-4) + 2);
        x_d[i] = ((r * DOT));

        r = (int) (Math.random() * (RAND_POS-4) + 2);
        y_d[i] = ((r * DOT + A_HEIGHT));
        z_d[i] = 20;
        
        for (int z = 0; z < 15; z++) {
            if ((x_a1 < x_d[z] + 3*DOT) && (x_a1 > x_d[z] -1*DOT) && (y_a1 < y_d[z] + 3*DOT) && (y_a1 > y_d[z] - 1*DOT)){
                locateApple();}
            if ((x_a2 < x_d[z] + 3*DOT) && (x_a2 > x_d[z] -1*DOT) && (y_a2 < y_d[z] + 3*DOT) && (y_a2 > y_d[z] - 1*DOT)){
                locateApple2();}
            if ((x_a3 < x_d[z] + 3*DOT) && (x_a3 > x_d[z] -1*DOT) && (y_a3 < y_d[z] + 3*DOT) && (y_a3 > y_d[z] - 1*DOT)){
                locateApple3();   
            }}
    }

    private void locateBubbles() { //creation process of the bubbles

        boolean phase1 = true;
        boolean phase2 = true;
        boolean upper = false;
        int sad = 5;
        int r = (int) (Math.random() * 5);
        if (r < 2){ // bubbles do not appear always it is randomly decided here
            phase1 = false;
            phase2 = false;
        }

        while (phase1) { //creates first bubble

        r = (int) (Math.random() * RAND_POS + 1);
        x_bubble1 = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_bubble1 = ((r * DOT + A_HEIGHT));

        for (int z = 0; z < 27; z++) {
                if ((directionW == 8) || (directionW == 2)){ //checks if the bubble has ended up in water if so repeats the process
                    if (x_bubble1 > x_w[0] + DOT){
                        phase1 = false;
                        upper = true;
                    } 
                    if (x_bubble1 < x_w[0] - DOT){
                        phase1 = false;
                    }
                } else {
                    if (y_bubble1 > y_w[0] + DOT){
                        phase1 = false;
                        upper = true;
                    }
                    if (y_bubble1 < y_w[0] - DOT){
                        phase1 = false;
                    }
                }
            }

        }    
    
    while (phase2 && sad > 1) { //creates second bubble only if the bubble appears on the opposite side of the water stream
        sad--; //it repeats the process only three times
     
        r = (int) (Math.random() * RAND_POS + 1);
        x_bubble2 = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_bubble2 = ((r * DOT + A_HEIGHT));
    
        for (int z = 0; z < 27; z++) {
                if ((directionW == 8) || (directionW == 2)){
                    if ((x_bubble2 > x_w[0] + DOT) && (upper == false)){
                        phase2 = false;
                        logicalTrack = true;
                    } 
                    if ((x_bubble2 < x_w[0] - DOT) && (upper)){
                        phase2 = false;
                        logicalTrack = true;
                    }
                } else {
                    if ((y_bubble2 > y_w[0] + DOT) && (upper == false)){
                        phase2 = false;
                        logicalTrack = true;
                    }
                    if ((y_bubble2 < y_w[0] - DOT) && (upper)){
                        phase2 = false;
                        logicalTrack = true;
                    }
                }
            }
        }
            z_bubble = 0;

        if (sad < 2) {
            x_bubble2 = -DOT;
            y_bubble2 = -DOT;
        }
        if (logicalTrack == true){ //if both bubbles are present, music will change
            audio2 = water01;
        }
    }
    private void locateApple2() { //creation process of the apple
        int r = (int) (Math.random() * RAND_POS + 1);
        x_a2 = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_a2 = ((r * DOT + A_HEIGHT));
        z_a2 = 20;

        for (int z = 0; z < 15; z++) {
            if ((x_a2 < x_d[z] + 3*DOT) && (x_a2 > x_d[z] -1*DOT) && (y_a2 < y_d[z] + 3*DOT) && (y_a2 > y_d[z] - 1*DOT)){
                locateApple2();  
            }}
    }

    private void locateApple3() { //creation process of the lightning bolt
        int r = (int) (Math.random() * RAND_POS + 1);
        x_a3 = ((r * DOT));

        r = (int) (Math.random() * RAND_POS + 1);
        y_a3 = ((r * DOT + A_HEIGHT));
        z_a3 = 10;

        for (int z = 0; z < 15; z++) {
            if ((x_a3 < x_d[z] + 3*DOT) && (x_a3 > x_d[z] -1*DOT) && (y_a3 < y_d[z] + 3*DOT) && (y_a3 > y_d[z] - 1*DOT)){
                locateApple3();  
            }}
    }

    private void prepareWater(){ //prepares the water stream to be used in game
        dx = 0;
        dy = 0;
        playTrack(s_water_spawn);
        for (int z = 0; z < 27; z++) { //default position for the water chunks
            x_w[z] = -x_w[z] - 20*DOT;
            y_w[z] = -y_w[z] - 20*DOT;
        }
        
        int r = (int) (Math.random() * (RAND_POS + 4)); //decides the direction from which will the water stream come
        waterSide = ((r * DOT)); //decides relative position of the water stream
        int full = ((RAND_POS + 2) * DOT);
        r = (int) (Math.random() * 4);
        switch (r) { //used to get information and coordinates of the start of the water stream
            case 1:
            x_water = 0;
            y_water = waterSide;
            directionW = 6;
            dx = -1;
                break;

            case 2:
            x_water = waterSide;
            y_water = DOT * 2;
            directionW = 2;
            dy = -1;        
                break;
            case 3:
            x_water = full;
            y_water = waterSide;    
            directionW = 4;
            dx = 1;
                break;
            default:
            x_water = waterSide;
            y_water = full + (DOT * 2);
            directionW = 8;
            dy = 1;
                break;
        }
        z_water = 10;
        for (int z = 0; z < 27; z++) { //defines the water stream
            x_w[z] = x_water + (z+1) * dx * DOT;
            y_w[z] = y_water + (z+1) * dy * DOT;
        }
    }

    private void locateWater() { //creates the water stream
        for (int z = 0; z < 27; z++) {
            x_w[z] = x_water + z * dx * DOT;
            y_w[z] = y_water + z * dy * DOT;
        }
        stop = false; //allows movement of the water stream
        coolwater = time;
        
    }

    private void moveEx() { //movement of the water stream
        if (stop == false){
            //if movement is allowed 
        
        for (int d = 26; d > 0; d--) { //it moves the back water chunks to positions of the previous water chunks
                x_w[d] = x_w[(d - 1)];
                y_w[d] = y_w[(d - 1)];
        }

        if (directionW == 4) { //the first water chunk moves according to the directionW (8 - up, 6 - right, 4 - left, 2 - down)
            x_w[0] -= DOT;
        }

        if (directionW == 6) {
            x_w[0] += DOT;
        }

        if (directionW == 8) {
            y_w[0] -= DOT;
        }

        if (directionW == 2) {
            y_w[0] += DOT;
        }
    }
    }

    private void move() { //movement of snake

        for (int z = dots; z > 0; z--) { //it moves snake chunks to positions of the previous snake chunks
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (direction == 4) { //the head moves according to the direction (8 - up, 6 - right, 4 - left, 2 - down)
            x[0] -= DOT;
            x_off -= 1;
            directionOld = false;
        }

        if (direction == 6) {
            x[0] += DOT;
            x_off += 1;
            directionOld = false;
        }

        if (direction == 8) {
            y[0] -= DOT;
            y_off -= 1;
            directionOld = false;
        }

        if (direction == 2) {
            y[0] += DOT;
            y_off += 1;
            directionOld = false;
        }
        

    }

    private void moveBomb() { //movement of the bomb
        if (phase > 3){ //movement of the bomb is allowed from the point when it is possible to find a bomb 
        if (directionB == 4) { //bomb moves according to the direction (8 - up, 6 - right, 4 - left, 2 - down, 5 - stationary)
            x_bomb -= DOT;
        }

        if (directionB == 6) {
            x_bomb += DOT;
        }

        if (directionB == 8) {
            y_bomb -= DOT;
        }

        if (directionB == 2) {
            y_bomb += DOT;
        }
        }

    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) { //checks if snake has not eaten him self
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (dots < 1){ //checks if snake is long enough to be a snake
            inGame = false;
        }

        if (inWater && !inBubble){ //checks if snake has a bubble while being in water
            inGame = false;
        } 
        if (inWater && inBubble){   
            inWaterBeen = true; //allows for better transition between being in water and not (NOT implemented)
        }

        if (inWaterBeen && inBubble){ //destroys the water stream after leaving it
            inBubble = false;
            inWaterBeen = false;
            stop = true;
            for (int z = 0; z < 27; z++) { //default position for the water chunks
                x_w[z] = - 20*DOT;
                y_w[z] = - 20*DOT;
            }
            x_bubble1 = -DOT; //removes bubbles
            y_bubble1 = -DOT;
            x_bubble2 = -DOT;
            y_bubble2 = -DOT;
        } 

        if (y[0] >= B_HEIGHT + A_HEIGHT) { //checks if the snake is in the play area
            inGame = false;
        }

        if (y[0] < A_HEIGHT) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (y_w[0] >= B_HEIGHT + A_HEIGHT) { //stops water after it left the play area
            stop = true;
        }

        if (y_w[0] < A_HEIGHT) {
            stop = true;
        }

        if (x_w[0] >= B_WIDTH) {
            stop = true;
        }

        if (x_w[0] < 0) {
            stop = true;
        }

        if (y_bomb >= B_HEIGHT + A_HEIGHT) { //bounces the bomb back into the play area
            directionB = 8;
        }

        if (y_bomb < A_HEIGHT) {
            directionB = 2;
        }

        if (x_bomb >= B_WIDTH) {
            directionB = 4;
        }

        if (x_bomb < 0) {
            directionB = 6;
        }

        if (!inGame && canDie) { //ends the game if necessary
            timer.stop();
            screen = 2;
        }

        if (!canDie){
            inGame = true;
        }

        
    }

    @Override
    public void actionPerformed(ActionEvent e) { //infinite loop being called a by a timer with a delay

        if (inGame) {
            if (screen == 0){
            if (time2 % 2 == 0){
            checkApple(); //checks if the object has been eaten
            if (phase > 2){
            checkWater(); //checks if player touched water
            }
            checkCollision(); //checks if player is supposed to be alive
            if (time % 2 == 0 || cooltime > 0){
            move(); //moves the snake
            }
            moveEx(); //moves the water stream 
            checkTime(); //counts down z coordinates (time of appear)
            event(); //checks if another phase has started and controls the difficulty of the game
            //Debug = time;
            timer.setDelay(DELAY + delay_time); //updates the delay 
        }
            if (clip.getFrameLength() <= clip.getLongFramePosition()){ //plays another music track when the last has ended
                if (logicalTrack){ //plays special track when said 
                    clip = playTrack(audio2);
                    logicalTrack = false;
                }else{ 
                if (music % 5 < 2){ //chooses between the pools of music tracks for consistency
                    music();
                } else {
                    altmusic();
                }}
                music++;
            }
            checkBomb(); //checks ations related to bombs and if player has crushed into the wall
            moveBomb(); //moves the bomb
    
            switch (time % 10) { //switches between the frames of the apple animation
                case 1,2,3,4:
                apple2 = apple2F0;
                    break;
                case 6,7,8,9:
                apple2 = apple2F2;    
                    break;
                default:
                apple2 = apple2F1;
                    break;
            }
            
            time2++;
            switch (direction) { //switches between the currently used texture for head (8 - up, 6 - right, 4 - left, 2 - down)
                case 4:
                    used_head = head[0];
                    break;
                case 6:
                    used_head = head[1];
                    break;
                case 8:
                    used_head = head[2];
                    break;
                default:
                    used_head = head2[time%12];
                    break;
            }
        }
        }

        repaint(); // refreshes/updates all of the graphical code to be updated on the screen
    }

    private void event() { // changes the difficulty of the game, higher score equals higher difficulty
        //phase 1 - only pear appears
        if (score > 50 && phase == 0){ //phase 2 - apple appears
            cooldown = time;
            phase++;
        }
        if (time == (cooldown + 30) && phase == 1){
            locateApple2();
        }
        if (score > 150 && phase == 1){ //phase 3 - lightning bolt appears
            cooldown = time;
            phase++;
        }
        if (time == (cooldown + 30) && phase == 2){
            locateApple3();
        }
        if (score > 300 && phase == 2){ //phase 4 - obstacles (water stream and walls) can appear, but rarely
            cooldown = time;
            coolwater = 0;
            for (int z = 0; z < 27; z++) { //default position for the water chunks
                x_w[z] = - 20*DOT;
                y_w[z] = - 20*DOT;
            }
            phase++;
        }
        if (score > 600 && phase == 3){ //phase 5 - more obstacles (water stream and walls) can appear and also bomb can help clean the obstacles
            cooldown = time;
            locateBomb();
            coolwater = 0;
            for (int z = 0; z < 27; z++) { //default position for the water chunks
                x_w[z] = - 20*DOT;
                y_w[z] = - 20*DOT;
            }
            water_duration = 25;
            phase++;
        }
        if (score > 1000 && phase == 4){ //phase 6 (end game) - even more obstacles (water stream and walls) appear, also there can appear multiple obstacles at once
            cooldown = time;
            coolwater = 0;
            for (int z = 0; z < 27; z++) { //default position for the water chunks
                x_w[z] = - 20*DOT;
                y_w[z] = - 20*DOT;
            }
            water_duration = 40;
            phase++;
        }
        if (phase == 3){ //creates obstacles for phases 4, 5 and 6
            if (time%800 == 0){
                cooldown = time;
                prepareWater();
                locateBubbles();
            }
            if (time == (cooldown + 10)){
                locateWater();
                firstwater = true;
            }
            if (time%800 == 400){ 
                boolean a = true;
                int i = -1;
                while (a){
                    i++;
                    if (x_d[i%15] == -5*DOT){
                        a = false;
                    }                    
                }
                locateWall(i%15);
            }
        }
        if (phase == 4){
            if (time%400 == 0){
                cooldown = time;
                prepareWater();
                locateBubbles();
            }
            if (time == (cooldown + 10)){
                locateWater();
            }
            if (time%400 == 200){
                boolean a = true;
                int i = -1;
                while (a){
                    i++;
                    if (x_d[i%15] == -5*DOT){
                        a = false;
                    }                    
                }
                locateWall(i%15);
            }
        }
        if (phase == 5){
            if (time%400 == 0 || time%400 == 100 || time%400 == 300){
                cooldown = time;
                prepareWater();
                locateBubbles();
            }
            if (time == (cooldown + 10)){
                locateWater();
            }
            if (time%400 == 200 || time%400 == 100 || time%400 == 300){
                boolean a = true;
                int i = -1;
                while (a){
                    i++;
                    if (x_d[i%15] == -5*DOT){
                        a = false;
                    }                    
                }
                locateWall(i%15);
            }
        }
    }

    private void music() { //chooses randomly music track
        int r;
        if (score < 300){ //last music track can be played after 300 score (obstacles can appear)
        r = (int) (Math.random() * 3);
        } else {
        r = (int) (Math.random() * 4);
        }
        if (randomOld == r){
            r++;
            if (r > 4){
                r = 1;
            }
        }
        switch (r) { 
            case 1:
                audio = main01;
                break;
            case 2:
                audio = main02;
                break;
            case 3:
                audio = main03;
                break;
            case 4:
                audio = main04;
                break;
            default:
                break;
        }
        clip = playTrack(audio); //plays the music track
        randomOld = r; //saves the number of the played music track
    }

    private void altmusic() { //chooses randomly music track
        int r;
        if (score < 300){ //last music track can be played after 300 score (obstacles can appear)
        r = (int) (Math.random() * 3);
        } else {
        r = (int) (Math.random() * 4);
        }
        if (randomOld2 == r){
            r++;
            if (r > 4){
                r = 1;
            }
        }
        switch (r) { 
            case 1:
                audio = alt01;
                break;
            case 2:
                audio = alt02;
                break;
            case 3:
                audio = alt03;
                break;
            case 4:
                audio = alt04;
                break;   
            default:
                break;
        }
        clip = playTrack(audio); //plays the music track
        randomOld2 = r; //saves the number of the played music track
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) { //used for the keepboard control

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (direction != 6) && (directionOld == false) || (key == KeyEvent.VK_A) && (direction != 6) && (directionOld == false)) {
                direction = 4;
                directionOld = true;
                delay_time = 0; //resets speed of the snake
            }

            if ((key == KeyEvent.VK_RIGHT) && (direction != 4) && (directionOld == false) || (key == KeyEvent.VK_D) && (direction != 4) && (directionOld == false)) {
                direction = 6;
                directionOld = true;
                delay_time = 0;
            }

            if ((key == KeyEvent.VK_UP) && (direction != 2) && (directionOld == false) || (key == KeyEvent.VK_W) && (direction != 2) && (directionOld == false)) {
                direction = 8;
                directionOld = true;
                delay_time = 0;
            }

            if ((key == KeyEvent.VK_DOWN) && (direction != 8) && (directionOld == false) || (key == KeyEvent.VK_S) && (direction != 8) && (directionOld == false)) {
                direction = 2;
                directionOld = true;
                delay_time = 0;
            }

            if ((key == KeyEvent.VK_R) && (inGame == false)){ //resets the game
                reinitBoard();
            }

            if ((key == KeyEvent.VK_R) && (screen == 1)){ //resets the game
                reinitBoard();     
            }
        }
    }
}