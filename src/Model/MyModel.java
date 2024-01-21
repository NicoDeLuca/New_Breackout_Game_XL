package Model;
import java.util.*;

/**
 * Die Klasse die für die Logik des Spiels verantwortlich ist.
 */
public class MyModel extends Thread implements IModel{

    private  List<float[]> blocks, balls, items, shots;
    ArrayList<Thread> threads;
    private float playerX ,playerWidth ,ballX ,ballY, ballSpeedX, ballSpeedY, ballRadius, itemX, itemY, itemSpeedY;
    private final float playerY, playerHeight;

    private int life,score;

    public MyModel() {
        itemSpeedY = 3;
        ballX = 440;
        ballY = 300;
        ballSpeedX = 0;
        ballSpeedY = 3;
        ballRadius = 10;
        playerX = 370.0F;
        playerY = 570.0F;
        playerWidth = 150;
        playerHeight = 20;
        life = 3;
        score = 0;
        items = new ArrayList<>();
        blocks = new ArrayList<>();
        threads = new ArrayList<>();
        balls = new ArrayList<>();
        shots = new ArrayList<>();
        balls.add(new float[]{ballX, ballY, ballSpeedX, ballSpeedY, ballRadius});
        createBlockRows();
    }

    /**
     * Erstellt die Blockreihen des Spiels.
     */
    public void createBlockRows() {
        for(int temp = 0; temp <= 6; temp++){
            final float c = temp;
            float k = 10 + c * 25;
            Thread t = new Thread(() -> {
                for(float j = k; j <= k + 15f; j += 25f){
                    for (float i = 17; i <= 733; i+=55f){
                        float[] l = {i, j, c};
                        createBlock(l);
                    }
                }
            });

            threads.add(t);

            t.start();
        }
    }

    /**
     *
     * @param blockcords übergibt die Blockkoordinaten, um damit dann die einzelnen Blöcke zu erstellen.
     */
    public void createBlock(float[] blockcords){
        blocks.add(new float[]{blockcords[0], blockcords[1], 50f, 20f, 0f,blockcords[2]});
    }


    /**
     * Lässt die Bälle sich Bewegen.
     */

    public void moveBall() {

        for (float[] ball : balls) {

            int steps = Math.max(Math.round(Math.abs(ball[2])), Math.round(Math.abs(ball[3])));
            float dx = ball[2] / steps;
            float dy = ball[3] / steps;


            for (int i = 0; i < steps; i++) {
                ball[0] += dx;
                ball[1] += dy;


                checkCollision(ball);
                checkBlockCollisions();
            }
        }
    }

    /**
     *
     * @param ball übergibt die Koordinaten der verschiedenen Bälle um diese dann mit denen des Spielers und der
     *             Wände auf Kollision zu überprüfen.
     */
    public void checkCollision(float[] ball) {
        if (ball[0] - ball[4] < 0) {
            ball[2] = Math.abs(ball[2]);
        } else if (ball[0] + ball[4] > 800) {
            ball[2] = -Math.abs(ball[2]);
        }

        if (ball[1] - ball[4] < 0) {
            ball[3] = Math.abs(ball[3]);
        } else if (ball[1] + ball[4] > playerY && ball[0] + ball[4] > playerX && ball[0] - ball[4] < playerX + playerWidth) {
            float ballPlayerHit = (ball[0] - (playerX + playerWidth / 2)) / (playerWidth / 2);

            float bounceAngle = ballPlayerHit * 75;
            ball[2] = (float) (7 * Math.sin(Math.toRadians(bounceAngle)));
            ball[3] = (float) (7 * -Math.abs(Math.cos(Math.toRadians(bounceAngle))));
        }
    }

    /**
     * Überprüft die Kollision zwischen Ball und Blöcken.
     */

    private void checkBlockCollisions() {
        for (float[] ball : balls) {
            Iterator<float[]> iterator = blocks.iterator();
            while (iterator.hasNext()) {
                float[] block = iterator.next();
                if (block[4] == 0) {
                    float blockX = block[0];
                    float blockY = block[1];
                    float blockWidth = block[2];
                    float blockHeight = block[3];
                    float testX = ball[0];
                    float testY = ball[1];

                    if (ball[0] < blockX) testX = blockX;
                    else if (ball[0] > blockX+blockWidth) testX = blockX+blockWidth;
                    if (ball[1] < blockY) testY = blockY;
                    else if (ball[1] > blockY+blockHeight) testY = blockY+blockHeight;

                    float distX = ball[0]-testX;
                    float distY = ball[1]-testY;
                    float distance = (float)Math.sqrt( (distX*distX) + (distY*distY) );

                    if (distance <= ball[4]) {
                        iterator.remove();
                        score = score + 100;

                        if (shouldDropItem()) {
                            itemX = block[0] + block[2] / 2;  // Mitte des Blocks
                            itemY = block[1] + block[3];  // Unterseite des Blocks
                            items.add(new float[]{itemX, itemY, itemSpeedY});
                        }

                        if (distX > 0) {
                            ball[2] = Math.abs(ball[2]);
                        } else if (distX < 0) {
                            ball[2] = -Math.abs(ball[2]);
                        }
                        if (distY > 0) {
                            ball[3] = Math.abs(ball[3]);
                        } else if (distY < 0) {
                            ball[3] = -Math.abs(ball[3]);
                        }
                    }
                }
            }
        }
    }


    /**
     *
     * @return gibt zurück, ob alle Blöcke getroffen wurden und somit ob der Spieler gewonnen hat.
     */


    public boolean isWinning() {
        boolean blocksOn = true;

        for (float[] block : blocks) {
            if (Math.abs(block[4]) == 0) {
                blocksOn = false;
                break;
            }
        }
        return blocksOn;
    }

    /**
     *
     * @return gibt zurück, ob ein Item erstellt werden soll anhand einer zufallszahl welche true ergibt, wenn sie unter 2 liegt.
     */
    public boolean shouldDropItem() {
        Random rand = new Random();
        int n = rand.nextInt(10);
        return n < 2;
    }

    /**
     * Lässt das item sich bewegen und überprüft die Kollision zwischen Spieler und Item.
     */

    public void itemCollision(){
        Random random = new Random();
        Iterator<float[]> iterator = items.iterator();

        while (iterator.hasNext()) {
            float[] item = iterator.next();
            // Aktualisieren Sie die Position des Items
            item[1] += item[2];

            // Überprüfen Sie, ob das Item den Spieler getroffen hat
            if (item[1] >= playerY && item[0] >= playerX && item[0] <= playerX + playerWidth) {
                // Das Item hat den Spieler getroffen, führen Sie hier den entsprechenden Code aus
                int randomNum = random.nextInt(3);  // Generiert eine zufällige Zahl zwischen 0 und 2

                switch (randomNum) {
                    case 0:
                        item1();
                        break;
                    case 1:
                        item2();
                        break;
                    case 2:
                        item3();
                        break;
                }

                // Entfernen Sie das Item aus der Liste
                iterator.remove();
            } else if (item[1] > 610) {
                // Das Item hat den unteren Rand des Spielfelds überschritten, entfernen Sie es aus der Liste
                iterator.remove();
            }
        }
    }
    /**
     * Erstellt das erste Item welches die Länge, des Spieler verlängert.
     */
    public void item1(){
        playerWidth = playerWidth +10;
    }

    /**
     * Erstellt das zweite Item welches einen weiteren Ball hinzufügt.
     */
    public void item2() {
        float middleOfPlayer = playerX + playerWidth / 2;
        balls.add(new float[]{middleOfPlayer, playerY, 0, -5, 10});
    }

    /**
     * Erstellt das dritte Item welches 10 Schüsse aus der Spieler mitte abfeuert.
     */
    public void item3() {
        new Thread(() -> {
            for(int i = 0; i < 10; i++){

                float middleOfPlayer = playerX + playerWidth / 2;
                shots.add(new float[]{middleOfPlayer, playerY, 0, -5, i});
                try {
                    // Pausiert den Thread für 500ms
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(); //fragen
                }
            }
        }).start();
    }

    /**
     * Lässt die Schüsse des dritten Items sich bewegen und entfernt sie, wenn sie das Spielfeld verlassen.
     */
    public void moveShots() {
        Iterator<float[]> iterator = shots.iterator();

        while (iterator.hasNext()) {
            float[] shot = iterator.next();



            shot[1] += shot[3];


            if (shot[1] < 0) {

                iterator.remove();
            }
        }
    }
    /**
     * Checkt die Kollision zwischen Schüssen und Blöcken und entfernt beide, wenn sie treffen.
     */
    public void checkShotBlockCollisions() {
        Iterator<float[]> shotIterator = shots.iterator();

        while (shotIterator.hasNext()) {
            float[] shot = shotIterator.next();
            Iterator<float[]> blockIterator = blocks.iterator();

            while (blockIterator.hasNext()) {
                float[] block = blockIterator.next();


                if (block[4] == 0 && shot[0] >= block[0] && shot[0] <= block[0] + block[2] &&
                        shot[1] >= block[1] && shot[1] <= block[1] + block[3]) {

                    shotIterator.remove();
                    blockIterator.remove();
                    score = score +100;


                    break;
                }
            }
        }
    }


    /**
     * @return gibt true zurück, sollte der Ball aus dem Spielfeld fliegen und der Spieler hat noch leben,
     * und false, wenn der Spieler keine Leben mehr hat. Vorher wird noch geschaut ob der ball der das Spielfeld verlässt
     * der letzte in der liste ist.
     */
    public boolean ballOutOfBounce() {
        for (int i = 0; i < balls.size(); i++) {
            float[] ball = balls.get(i);
            if (ball[1] + ball[4] > 610) {
                balls.remove(i);
                i--;  // Aktualisiere den Index
            }
        }

        // Wenn es keine Bälle mehr gibt, ziehe ein Leben ab und setze den Zustand zurück
        if (balls.isEmpty()) {
            life = life - 1;
            if (life == 0){
                resetGameLoose();
                return true;
                // Das Spiel ist beendet
            } else {
                // Fügt einen neuen Ball hinzu, wenn noch Leben übrig sind
                float middleOfPlayer = playerX + playerWidth / 2;
                balls.add(new float[]{middleOfPlayer, playerY, 0, -5, 10});
            }
        }
        return false;  // Das Spiel ist nicht beendet
    }

    /**
     * resettet das Game, wenn der Spieler das Spiel verloren hat.
     */
    public void resetGameLoose() {
        life = 3;
        score = 0;
        playerWidth = 150;
        blocks.clear();
        createBlockRows();

        // Fügt einen neuen Ball hinzu
        float middleOfPlayer = playerX + playerWidth / 2;
        balls.add(new float[]{middleOfPlayer, playerY, 0, -5, 10});
    }
    /**
     * resettet das Game, wenn der Spieler gewonnen hat.
     */
    public void resetGameWin() {

        createBlockRows();

        float middleOfPlayer = playerX + playerWidth / 2;
        balls.clear();
        items.clear();
        balls.add(new float[]{middleOfPlayer, playerY, 0, -5, 10});
    }
    /**
     * @return übergibt die Leben, die der Spieler noch hat.
     */
    public int getLife() {
        return life;
    }

    /**
     * @return übergibt den Score des Spielers.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return übergibt die X-Koordinate des Spielers.
     */
    public float getPlayerX() {
        return playerX;
    }

    /**
     * setzt die X-Koordinate des Spielers.
     */
    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    /**
     * @return übergibt die Y-Koordinate des Spielers.
     */
    public float getPlayerY() {
        return playerY;
    }

    /**
     * @return übergibt die Weite des Spielers.
     */
    public float getPlayerWidth() {
        return playerWidth;
    }

    /**
     * @return übergibt die Höhe des Spielers.
     */
    public float getPlayerHeight() {
        return playerHeight;
    }

    /**
     * @return übergibt die Blöcke.
     */
    public List<float[]> getBlocks () {
        return blocks;
    }

    /**
     * @return übergibt die Items.
     */
    public List<float[]> getItems() {
        return items;
    }

    /**
     * @return übergibt die Bälle.
     */
    public List<float[]> getBalls() {
        return balls;
    }

    /**
     * @return übergibt die Schüsse.
     */
    public List<float[]> getShots() {
        return shots;
    }
}


