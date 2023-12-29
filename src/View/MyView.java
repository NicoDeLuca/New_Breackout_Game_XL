


package View;
import Controller.MyController;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class MyView extends PApplet implements IView{

    private MyController controller;
    PImage BallBild;
    PImage SpielerBild;


    public void setController(MyController controller) {
        this.controller = controller;
    }

    public void settings() {
        size(800, 600);
        pixelDensity(2);
    }

    public void setup() {
        frameRate(60);
        BallBild = loadImage("Ball.png");
        SpielerBild = loadImage("Spieler.png");

    }
    public void draw() {
        background(0);
        drawPlayer(this,controller);
        keyPressed(this);
        drawBall(this,controller);
        controller.moveBall();
        drawBlocks(this,controller);

    }

    public void drawPlayer(PApplet g, MyController controller) {
        float playerX = controller.getModel().getPlayerX();
        float playerY = controller.getModel().getPlayerY();
        float playerWidth = controller.getModel().getPlayerWidth();
        float playerHeight = controller.getModel().getPlayerHeight();
        g.rect(playerX, playerY, playerWidth, playerHeight, 7); // Zeichnet den Spieler //mit gettern Arbeiten und Variablen in Privat ändern!
        image(SpielerBild,playerX +58,playerY,381,332);

    }

    public void keyPressed(PApplet g) {
        if (g.keyPressed) {
            if (g.key == 'd' || g.key == 'D') {
                controller.moveRight();
            }
            if (g.key == 'a' || g.key == 'A') {
                controller.moveLeft();
            }
        }
        drawPlayer(g, controller);
    }

    public void drawBall(PApplet g, MyController controller) {

        g.ellipse(controller.getBallX(), controller.getBallY(), 20, 20); // Zeichnet den Ball
        image(BallBild,controller.getBallX(),controller.getBallY(),40,40);
        imageMode(CENTER);
    }


    public void drawBlocks(PApplet g, MyController controller) {
        List<float[]> blocks = controller.getModel().getBlocks();
        for (float[] block : blocks) {
            if (block[4] == 0) { // Wenn der Block nicht zerstört ist
                g.rect(block[0], block[1], block[2], block[3]);
            }
        }
    }
}



