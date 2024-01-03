package Controller;
import View.*;
import Model.*;



public class MyController implements IController{



    private MyView view;

    private MyModel model;

    public MyController(MyView view, MyModel model) {

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
        float playerX = model.getPlayerX();
        if (playerX < 640) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            model.setPlayerX(playerX + 10); // Verschiebt den Spieler nach rechts
    }

    public void moveLeft() {
        float playerX = model.getPlayerX();
        if (playerX > 10) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            model.setPlayerX(playerX - 10); // Verschiebt den Spieler nach rechts
    }

    public float getBallX() {
        return model.getBallX();
    }

    public float getBallY() {
        return model.getBallY();
    }

    public float getBallRadius() {
        return model.getBallRadius();
    }

    public void moveBall() { //ruft die MoveBall Methode um sie dann in der View von hier aufrufen zu können.
        model.moveBall();
        model.checkBlockCollisions();

    }
    public void resetBall(){

    model.resetBall();
    }


    public MyModel getModel() {
        return model;
    }



}
