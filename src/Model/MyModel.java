package Model;
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
        blocks = new ArrayList<>();
        blocks.add(new float[]{10f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{70f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{130f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{190f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{250f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{310f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{370f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{430f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{490f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{550f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{610f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{670f, 10f, 50f, 20f, 0f});
        blocks.add(new float[]{730f, 10f, 50f, 20f, 0f});

        blocks.add(new float[]{10f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{70f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{130f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{190f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{250f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{310f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{370f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{430f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{490f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{550f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{610f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{670f, 40f, 50f, 20f, 0f});
        blocks.add(new float[]{730f, 40f, 50f, 20f, 0f});


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

    public void checkBlockCollisions(float ballX, float ballY, float ballRadius) {
        for (float[] block : blocks) {
            if (block[4] == 0) { // Wenn der Block nicht zerstört ist
                float blockX = block[0];
                float blockY = block[1];
                float blockWidth = block[2];
                float blockHeight = block[3];

                // Überprüfen Sie die Kollision zwischen dem Ball und dem Block
                if (ballX + ballRadius > blockX && ballX - ballRadius < blockX + blockWidth &&
                        ballY + ballRadius > blockY && ballY - ballRadius < blockY + blockHeight) {
                    block[4] = 1f; // Setzen Sie den Block auf "zerstört"

                    float diffX = Math.abs(ballX - (blockX + blockWidth / 2));
                    float diffY = Math.abs(ballY - (blockY + blockHeight / 2));

                    // Überprüfen Sie, ob der Ball hauptsächlich von oben oder unten auf den Block trifft
                    if (diffX < blockWidth / 2) {
                        ballSpeedY = -ballSpeedY; // Ändern Sie die Richtung des Balls in Y-Richtung
                    }

                    // Überprüfen Sie, ob der Ball hauptsächlich von links oder rechts auf den Block trifft
                    if (diffY < blockHeight / 2) {
                        ballSpeedX = -ballSpeedX; // Ändern Sie die Richtung des Balls in X-Richtung
                    }
                }
            }
        }
    }


    public List<float[]> getBlocks() {
        return blocks;
    }

}


