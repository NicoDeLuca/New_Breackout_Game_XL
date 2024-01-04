package Model;
import Controller.*;


import java.util.List;
import java.util.ArrayList;


public class MyModel  {

    private List<float[]> blocks;
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

    public float getBallRadius() {
        return ballRadius;
    }

    public MyModel() {
        ballX = 440;
        ballY = 300;
        ballSpeedX = 0;
        ballSpeedY = 6;
        ballRadius = 10;
        playerX = 370.0F;
        playerY = 570.0F;
        playerWidth = 150;
        playerHeight = 20;
        blocks = new ArrayList<>();

        for(float k = 10; k <= 25f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 0;
                float[] l = {i, k, c};
                createBlock(l);
            }
        }

        for(float k = 35; k <= 50f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 1;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }
        for(float k = 60; k <= 70f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 2;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }
        for(float k = 85; k <= 100f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 3;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }
        for(float k = 110; k <= 130f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 4;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }
        for(float k = 135; k <= 155f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 5;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }
        for(float k = 160; k <= 175f; k += 25f){
            for (float i = 15; i <= 730; i+=55f){
                float c = 6;
                float[] l = {i, k, c};

                createBlock(l);
            }
        }




    }
    public void createBlock(float[] blockcords){
        blocks.add(new float[]{blockcords[0], blockcords[1], 50f, 20f, 0f,blockcords[2]});
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

    public void moveBall() {
        int steps = Math.max(Math.round(Math.abs(ballSpeedX)), Math.round(Math.abs(ballSpeedY)));
        Math.round(Math.abs(ballSpeedY));
        float dx = ballSpeedX / steps;
        float dy = ballSpeedY / steps;

        for (int i = 0; i < steps; i++) {
            ballX += dx;
            ballY += dy;
            checkCollision();
            checkBlockCollisions();
        }
    }

    private void checkCollision() {
        if (ballX - ballRadius < 0) {
            ballSpeedX = Math.abs(ballSpeedX);
        } else if (ballX + ballRadius > 800) {
            ballSpeedX = -Math.abs(ballSpeedX);
        }

        if (ballY - ballRadius < 0) {
            ballSpeedY = Math.abs(ballSpeedY);
        } else if (ballY + ballRadius > playerY && ballX + ballRadius > playerX && ballX - ballRadius < playerX + playerWidth) {
            // Berechne, wo der Ball den Spieler getroffen hat, relativ zur Mitte des Spielers
            float relativeIntersect = (ballX - (playerX + playerWidth / 2)) / (playerWidth / 2);

            // Berechne den Abprallwinkel (75° bis -75°)
            float bounceAngle = relativeIntersect * 75;

            // Aktualisiere die Geschwindigkeit des Balls basierend auf dem Abprallwinkel
            ballSpeedX = (float) (6 * Math.sin(Math.toRadians(bounceAngle)));
            ballSpeedY = (float) (6 * -Math.abs(Math.cos(Math.toRadians(bounceAngle))));
        }
    }

    public void resetBall() {
        ballX = 400;
        ballY = 300;
        ballSpeedX = 0;
        ballSpeedY = 6;

    }


    public void checkBlockCollisions() {
        for (float[] block : blocks) {
            if (block[4] == 0) {
                float blockX = block[0];
                float blockY = block[1];
                float blockWidth = block[2];
                float blockHeight = block[3];
                float testX = ballX;
                float testY = ballY;

                if (ballX < blockX) testX = blockX;
                else if (ballX > blockX+blockWidth) testX = blockX+blockWidth;
                if (ballY < blockY) testY = blockY;
                else if (ballY > blockY+blockHeight) testY = blockY+blockHeight;

                float distX = ballX-testX;
                float distY = ballY-testY;
                float distance = (float)Math.sqrt( (distX*distX) + (distY*distY) );

                if (distance <= ballRadius) {
                    block[4] = 1f;
                    if (distX > 0) {
                        ballSpeedX = Math.abs(ballSpeedX);
                    } else if (distX < 0) {
                        ballSpeedX = -Math.abs(ballSpeedX);
                    }
                    if (distY > 0) {
                        ballSpeedY = Math.abs(ballSpeedY);
                    } else if (distY < 0) {
                        ballSpeedY = -Math.abs(ballSpeedY);
                    }
                }
            }
        }
    }



    public List<float[]> getBlocks () {
        return blocks;
    }

}


