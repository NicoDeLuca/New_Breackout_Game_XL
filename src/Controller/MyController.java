package Controller;
import View.IView;
import Model.IModel;
import java.util.List;

/**
 * Die Klasse die für as Kontrollieren des Spielers und die Kommunikation zwischen Model und View verantwortlich ist.
 */
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


    /**
     *  Lässt den Spieler sich mach rechts bewegen, bis zum Rand des Spielfelds.
     */

    public void moveRight() {
        float playerX = model.getPlayerX();
        float playerWidth = model.getPlayerWidth();
        if (playerX + playerWidth < 790) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            model.setPlayerX(playerX + 10); // Verschiebt den Spieler nach rechts
     // Verschiebt den Spieler nach rechts
    }

    /**
     *  Lässt den Spieler sich mach links bewegen, bis zum Rand des Spielfelds.
     */
    public void moveLeft() {
        float playerX = model.getPlayerX();
        if (playerX > 10) // Prüft, ob der Spieler noch auf dem Spielfeld ist
            model.setPlayerX(playerX - 10); // Verschiebt den Spieler nach rechts
    }


    /**
     *
     * @return übergibt die Weite des Spielers, um sie in der View Klasse nutzbar zu machen.
     */
    public float getPlayerWidth() {
        return model.getPlayerWidth();
    }

    /**
     *
     * @return übergibt die Leben des Spielers, um sie in der View Klasse nutzbar zu machen.
     */

    public int getLeben(){
        return model.getLife();
    }

    /**
     *
     * @return übergibt den Score des Spielers, um sie in der View Klasse nutzbar zu machen.
     */

    public int getScore(){
        return model.getScore();
    }

    /**
     *
     * @return übergibt die isWinning Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public boolean isWinning(){
        return model.isWinning();
    }

    /**
     *
     * übergibt die resetGameLoose Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public void resetGameLoose() {
        model.resetGameLoose();
    }

    /**
     *
     * übergibt die resetGameWin Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public void resetGameWin() {
        model.resetGameWin();
    }

    /**
     * übergibt verschiedene Methoden die in der Draw Methode aufgerufen werden müssen damit das Spiel läuft.
     */
    public void runGame() {
        model.moveBall();
        model.itemCollision();
        model.moveShots();
        model.checkShotBlockCollisions();
    }


    /**
     *
     * @return übergibt das Model, um es in der View Klasse nutzbar zu machen.
     */
    public IModel getModel() {
        return model;
    }

    /**
     *
     * @return übergibt die getItems Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */

    public List<float[]> getItems() {
        return model.getItems();
    }

    /**
     *
     * @return übergibt die getBalls Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public List<float[]> getBalls() {
        return model.getBalls();
    }

    /**
     *
     * @return übergibt die getShots Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public List<float[]> getShots() {
        return model.getShots();
    }

    /**
     *
     * @return übergibt die ballOutOfBounce Methode aus dem Model, um sie in der View Klasse nutzbar zu machen.
     */
    public boolean ballOutOfBounce() {
            return model.ballOutOfBounce();
        }


    }


