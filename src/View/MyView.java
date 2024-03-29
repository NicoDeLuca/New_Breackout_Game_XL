package View;
import Controller.IController;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;

/**
 * Die Klasse die für alle Grafischen Sachen des Spiels verantwortlich ist.
 */

public class MyView extends PApplet implements IView{
    private IController controller;
    PImage BallPicture;
    PImage PlayerPicture;
    PImage Bg;
    PImage block_01,block_02,block_03,block_04,block_05,block_06,block_07,block_08,item_01;

    private int gameHasStarted;

    private boolean left, right;

    /**
     *
     * @param controller übergibt den Kontroller.
     */
    public void setController(IController controller) {
        this.controller = controller;
    }

    /**
     * setzt die größe des Fensters des Spieles.
     */
    public void settings() {
        size(800, 600);
    }

    /**
     * Läd alle Variablen und Bilder.
     */
    public void setup() {
        noLoop();
        gameHasStarted = 1;
        frameRate(60);
        BallPicture = loadImage("Pictures/Ball.png");
        PlayerPicture = loadImage("Pictures/Spieler.png");
        Bg = loadImage("Pictures/Background.png");
        block_01 = loadImage("Pictures/block_01.png");
        block_02 = loadImage("Pictures/block_02.png");
        block_03 = loadImage("Pictures/block_03.png");
        block_04 = loadImage("Pictures/block_04.png");
        block_05 = loadImage("Pictures/block_05.png");
        block_06 = loadImage("Pictures/block_06.png");
        block_07 = loadImage("Pictures/block_07.png");
        block_08 = loadImage("Pictures/block_08.png");
        item_01 = loadImage("Pictures/item_01.png");
        left = false;
        right = false;
    }

    /**
     * Zeichnet alles was im Spiel passiert.
     */
    public void draw() {

        background(Bg);
        drawPlayer(controller);
        drawBalls();
        controller.runGame();
        drawBlocks(controller);
        playermovement();
        drawPlayer(controller);
        drawShots();
        textSize(12);
        text("Leben: "+controller.getLeben(), 730, 560);
        text("Score: "+controller.getScore(), 30, 560);
        gameStart();

        for (float[] item : controller.getItems()) {
            drawItem(item);
        }
        winOrLoose();



    }

    /**
     *
     * @param controller wird übergeben um dessen Methoden und die des Models hier nutzbar zu machen, um den Spieler zu zeichnen.
     */
    public void drawPlayer(IController controller) {

        rect(controller.getModel().getPlayerX(), controller.getModel().getPlayerY(), controller.getModel().getPlayerWidth(), controller.getModel().getPlayerHeight(), 7); // Zeichnet den Spieler //mit gettern Arbeiten und Variablen in Privat ändern!
        image(PlayerPicture,controller.getModel().getPlayerX(),controller.getModel().getPlayerY(),controller.getModel().getPlayerWidth(),controller.getModel().getPlayerHeight());

    }

    /**
     * überprüft, ob bestimmte Tasten gedrückt werden.
     */
    public void keyPressed() {
        switch (keyCode) {
            case(68)-> right = true;
            case(65)-> left = true;

        }
        if(key == 27){
            key = 0;
        }

        if(keyCode == ESC){
            if(looping){
                noLoop();
            }
            else{
                loop();
                gameHasStarted = 0;
            }
        }


    }

    /**
     * Schaut, ob die Tasten A und D gedrückt sind, um die Steuerung zu verbessern.
     */
    public void keyReleased() {
        switch (keyCode) {
            case(68)-> right = false;
            case(65)-> left = false;
        }
    }

    /**
     * Verknüpft die Bewegung des Spielers mit den Tasten A und D.
     */
    public void playermovement(){
        if(right)
            controller.moveRight();
        if(left)
            controller.moveLeft();
    }

    /**
     * Zeichnet die Bälle.
     */
    public void drawBalls() {
        for (float[] ball : controller.getBalls()) {
            float ballX = ball[0];
            float ballY = ball[1];
            ellipse(ballX, ballY, 20, 20);
            image(BallPicture, ballX - 10, ballY - 10);
        }
    }

    /**
     * Zeichnet die Schüsse des 3. Items
     */

    public void drawShots() {
        for (float[] shot : controller.getShots()) {
            float shotX = shot[0];
            float shotY = shot[1];
            fill(222,159,223);
            ellipse(shotX, shotY, 10, 10);
            fill(255);
        }
    }

    /**
     *
     * @param item übergibt die Koordinaten, um das Item zu zeichnen.
     */
    public void drawItem(float[] item) {
        image(item_01,item[0] -10,item[1]-10);
    }

    /**
     *
     * @param controller wird übergeben, um die Blöcke zu zeichnen.
     */
    public void drawBlocks(IController controller) {
        List<float[]> blocks = controller.getModel().getBlocks();
        for (float[] block : blocks) {
            if (block[4] == 0 && block[5] == 0) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_01,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 1) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_02,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 2) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_03,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 3) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_04,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 4) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_05,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 5) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_06,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 6) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_07,block[0], block[1]);

            }
            if (block[4] == 0 && block[5] == 7) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3],3);
                image(block_08,block[0], block[1]);

            }
        }
    }

    /**
     * Schaut ob der Spieler gewinnt oder verliert.
     */
    public void winOrLoose(){
        if (controller.ballOutOfBounce()) {
            textSize(40);
            noLoop();
            text("Sie haben verloren.",250,300);
        }

        if (controller.isWinning()){
            noLoop();
            textSize(40);
            text("Sie haben gewonnen.",230,300);
            controller.resetGameWin();}

        if (controller.getPlayerWidth() > 770){
            noLoop();
            textSize(40);
            text("Jetzt haben sie tatsächlich gewonnen.",110,300);
            controller.resetGameLoose();}
    }

    /**
     * Zeichnet den Text, der am Anfang infos gibt.
     */
    public void gameStart(){
        if(gameHasStarted == 1){
            textSize(40);
            text("Use A and D to move.",220,230);
            text("Press ESC to Start.",250,280);


        }
    }


}