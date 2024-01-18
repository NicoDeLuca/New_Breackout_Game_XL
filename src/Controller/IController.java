package Controller;

import Model.IModel;
import View.IView;


import java.util.List;

public interface IController {

    void setView(IView view);
    void setModel(IModel model);
    void moveRight();
    void moveLeft();
    float getPlayerWidth();
    float getItemY();
    int getLeben();
    int getScore();
    boolean isWinning();
    void resetGameLoose();
    void resetGameWin();
    void moveBall();
    IModel getModel();
    List<float[]> getItems();
    List<float[]> getBalls();
    List<float[]> getShots();
    boolean ballOutOfBounce();
}
