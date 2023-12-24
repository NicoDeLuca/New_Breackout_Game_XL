package Model;
import Controller.*;



public class MyModel  {

    private float playerX;
    private float playerY;
    private float playerWidth;
    private float playerHeight;
    private float ballX;
    private float ballY;
    public float ballSpeedX;
    public float ballSpeedY;
    private  float ballRadius;



    public float getBallX() {
        return ballX;
    }

    public float getBallY() {
        return ballY;
    }

    public MyModel() {
        ballX = 400;
        ballY = 300;
        ballSpeedX = 4;
        ballSpeedY = 4;
        ballRadius = 10;
        playerX = 570.0F;
        playerY = 570.0F;
        playerWidth = 150;
        playerHeight = 20;

    }

    public float getPlayerX() {
        return playerX;
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public float getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(float playerWidth) {
        this.playerWidth = playerWidth;
    }

    public float getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(float playerHeight) {
        this.playerHeight = playerHeight;
    }

    public void moveBall() { //lässt den Ball sich bewegen
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        checkCollision();
    }

    private void checkCollision() {
        if (ballX - ballRadius < 0 || ballX + ballRadius > 800) {
            ballSpeedX = -ballSpeedX; // Ändert die Richtung des Balls in x-Richtung
        }

        if (ballY - ballRadius < 0) {
            ballSpeedY = -ballSpeedY; // Ändert die Richtung des Balls in y-Richtung
        }

        // Kollision mit dem Spieler
        if (ballX + ballRadius > playerX && ballX - ballRadius < playerX + playerWidth &&
                ballY + ballRadius > playerY && ballY - ballRadius < playerY + playerHeight) {
            ballSpeedY = -ballSpeedY; // Ändert die Richtung des Balls in y-Richtung
        }
    }


}


