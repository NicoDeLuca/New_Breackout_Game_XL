package View;
import Controller.MyController;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;

public class MyView extends PApplet implements IView{
    private MyController controller;
    PImage BallBild;
    PImage SpielerBild;
    PImage Bg;

    PImage block_01,block_02,block_03,block_04,block_05,block_06,block_07,block_08;

    private boolean left, right;

    public void setController(MyController controller) {
        this.controller = controller;
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        frameRate(60);
        BallBild = loadImage("Ball.png");
        SpielerBild = loadImage("Spieler.png");
        Bg = loadImage("Background.png");
        block_01 = loadImage("block_01.png");
        block_02 = loadImage("block_02.png");
        block_03 = loadImage("block_03.png");
        block_04 = loadImage("block_04.png");
        block_05 = loadImage("block_05.png");
        block_06 = loadImage("block_06.png");
        block_07 = loadImage("block_07.png");
        block_08 = loadImage("block_08.png");
        left = false;
        right = false;
    }
    public void draw() {
        background(Bg);
        drawPlayer(controller);
        drawBall(controller);
        controller.moveBall();
        drawBlocks(controller);
        playermovement();
        drawPlayer(controller);
        ballOutOfBounce();


    }

    public void drawPlayer(MyController controller) {
        float playerX = controller.getModel().getPlayerX();
        float playerY = controller.getModel().getPlayerY();
        float playerWidth = controller.getModel().getPlayerWidth();
        float playerHeight = controller.getModel().getPlayerHeight();
        rect(playerX, playerY, playerWidth, playerHeight, 7); // Zeichnet den Spieler //mit gettern Arbeiten und Variablen in Privat ändern!
        image(SpielerBild,playerX,playerY);

    }

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
            }
        }
        if (keyCode == ENTER){
            frameRate(20);
        }

    }

    public void keyReleased() {
        switch (keyCode) {
            case(68)-> right = false;
            case(65)-> left = false;
        }
    }

    public void playermovement(){
        if(right)
            controller.moveRight();
        if(left)
            controller.moveLeft();
    }

    public void drawBall(MyController controller) {
        ellipse(controller.getBallX(), controller.getBallY(), 20, 20); // Zeichnet den Ball
        image(BallBild,controller.getBallX() -10,controller.getBallY()-10);

    }

    public void drawBlocks(MyController controller) {
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

    public void ballOutOfBounce(){

        if (controller.getBallY() + controller.getBallRadius() > 630) {
            // Wenn der Ball das Spielfeld unterhalb des Spielers verlässt, setze ihn zurück
            controller.resetBall();
            noLoop();

        }

    }

}