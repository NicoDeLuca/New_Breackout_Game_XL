package Model;

import java.util.List;

public interface IModel {
    void createBlock(float[] blockcords);
    int getLife();
    int getScore();
    float getPlayerX();
    void setPlayerX(float playerX);
    float getPlayerY();
    float getPlayerWidth();
    float getPlayerHeight();

    void moveBall();
    List<float[]> getItems();
    List<float[]> getBalls();
    List<float[]> getShots();
    List<float[]> getBlocks();
    boolean isWinning();
    boolean shouldDropItem();
    void itemCollision();
    void item1();
    void item2();
    void item3();
    void moveShots();
    void checkShotBlockCollisions();
    boolean ballOutOfBounce();
    void resetGameLoose();
    void resetGameWin();


}

