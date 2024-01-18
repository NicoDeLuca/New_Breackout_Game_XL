package View;

import Controller.IController;
import Controller.MyController;

public interface IView {

    void setController(IController controller);
    void settings();
    void setup();
    void draw();
    void drawPlayer(IController controller);
    void keyPressed();
    void keyReleased();
    void playermovement();
    void drawBalls();
    void drawShots();
    void drawItem(float[] item);
    void drawBlocks(IController controller);
}
