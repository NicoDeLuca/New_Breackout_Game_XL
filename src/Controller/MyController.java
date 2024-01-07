package Controller;
import View.*;
import Model.*;
import java.util.List;


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
        float playerWidth = model.getPlayerWidth();
        if (playerX + playerWidth < 790) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            model.setPlayerX(playerX + 10); // Verschiebt den Spieler nach rechts
     // Verschiebt den Spieler nach rechts
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

    public float getItemX() {
        return model.getItemX();
    }

    public float getPlayerY() {
        return model.getPlayerY();
    }

    public float getPlayerX() {
        return model.getPlayerX();
    }

    public float getPlayerWidth() {
        return model.getPlayerWidth();
    }

    public float getItemY() {
        return model.getItemY();
    }

    public int getLeben(){
        return model.getLeben();
    }


    public int getScore(){
        return model.getScore();
    }
    public boolean isWinning(){
        return model.isWinning();
    }

//    public boolean isGameOver() {
//        return model.isGameOver();
//    }


    public void moveBall() { //ruft die MoveBall Methode um sie dann in der View von hier aufrufen zu können.
        model.moveBall();
        model.checkBlockCollisions();
        model.itemCollision();

    }



    public MyModel getModel() {
        return model;
    }

    public List<float[]> getItems() {
        return model.getItems();
    }

    public List<float[]> getBalls() {
        return model.getBalls();
    }



        public boolean ballOutOfBounce() {
            return model.ballOutOfBounce();
        }
    }


