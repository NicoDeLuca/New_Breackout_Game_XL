package Controller;
import View.*;
import Model.*;



public class MyController implements IController{

    public float player_y; // y-Position des Spielers
    public float player_x; // x-Position des Spielers

    private MyView view;

    private MyModel model;

    public MyController(float player_y, float player_x, MyView view, MyModel model) {
        this.player_y = player_y;
        this.player_x = player_x;
        this.view = view;
        this.model = model;
    }

    public void setView(MyView view) {
        this.view = view;
    }

    public void setModel(MyModel model) {
        this.model = model;
    }


    public void moveRight() {
        if (player_x < 640) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            player_x = player_x + 10; // Verschiebt den Spieler nach rechts
    }

    public void moveLeft() {
        if (player_x > 10) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            player_x = player_x - 10; // Verschiebt den Spieler nach links
    }

    public float getBallX() {
        return model.ballX;
    }

    public float getBallY() {
        return model.ballY;
    }

    public void moveBall() { //ruft die MoveBall Methode um sie dann in der View von hier aufrufen zu können.
        model.moveBall();
    }

}
