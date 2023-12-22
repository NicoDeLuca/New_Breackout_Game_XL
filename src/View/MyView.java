package View;
import Controller.MyController;
import processing.core.PApplet;

public class MyView extends PApplet implements IView{

    private MyController controller;



    public void setController(MyController controller) {
        this.controller = controller;
    }

    public void settings() {
        size(800, 600);
        pixelDensity(2);
    }

    public void setup() {
        frameRate(60);

    }
    public void draw() {
        background(0);
        drawPlayer(this,controller);
        keyPressed(this);
        drawBall(this,controller);
        controller.moveBall();

    }

    public void drawPlayer(PApplet g, MyController controller) {
        g.rect(controller.player_x, controller.player_y, 150, 20, 7); // Zeichnet den Spieler
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
    }
}



