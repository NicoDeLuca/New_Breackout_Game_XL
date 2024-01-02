package View;
import Controller.MyController;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;

public class MyView extends PApplet implements IView{
    private MyController controller;
    PImage BallBild;
    PImage SpielerBild;

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
        left = false;
        right = false;
    }
    public void draw() {
        background(0);
        drawPlayer(controller);
        drawBall(controller);
        controller.moveBall();
        drawBlocks(controller);
        playermovement();
        drawPlayer(controller);
        
    }

    public void drawPlayer(MyController controller) {
        float playerX = controller.getModel().getPlayerX();
        float playerY = controller.getModel().getPlayerY();
        float playerWidth = controller.getModel().getPlayerWidth();
        float playerHeight = controller.getModel().getPlayerHeight();
        rect(playerX, playerY, playerWidth, playerHeight, 7); // Zeichnet den Spieler //mit gettern Arbeiten und Variablen in Privat ändern!
        image(SpielerBild,playerX +58,playerY,381,332);

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
        image(BallBild,controller.getBallX(),controller.getBallY(),40,40);
        imageMode(CENTER);
    }

    public void drawBlocks(MyController controller) {
        List<float[]> blocks = controller.getModel().getBlocks();
        for (float[] block : blocks) {
            if (block[4] == 0) { // Wenn der Block nicht zerstört ist
                rect(block[0], block[1], block[2], block[3]);
            }
        }
    }

}