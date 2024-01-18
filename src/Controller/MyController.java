package Controller;
import View.IView;
import Model.IModel;
import java.util.List;


public class MyController implements IController{

    private IView view;

    private IModel model;

    public MyController(IView view, IModel model) {

        this.view = view;
        this.model = model;
    }



    public void setView(IView view) {
        this.view = view;
    }

    public void setModel(IModel model) {
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


    public float getPlayerWidth() {
        return model.getPlayerWidth();
    }

    public float getItemY() {
        return model.getItemY();
    }

    public int getLeben(){
        return model.getLife();
    }


    public int getScore(){
        return model.getScore();
    }
    public boolean isWinning(){
        return model.isWinning();
    }

    public void resetGameLoose() {
        model.resetGameLoose();
    }

    public void resetGameWin() {
        model.resetGameWin();
    }


    public void moveBall() { //ruft die MoveBall Methode um sie dann in der View von hier aufrufen zu können.
        model.moveBall();
        model.checkBlockCollisions();
        model.itemCollision();                  //neu benennen
        model.moveShots();
        model.checkShotBlockCollisions();
    }


    public IModel getModel() {
        return model;
    }

    public List<float[]> getItems() {
        return model.getItems();
    }

    public List<float[]> getBalls() {
        return model.getBalls();
    }

    public List<float[]> getShots() {
        return model.getShots();
    }

    public boolean ballOutOfBounce() {
            return model.ballOutOfBounce();
        }


    }


