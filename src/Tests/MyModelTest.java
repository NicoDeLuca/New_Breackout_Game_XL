package Tests;
import Model.MyModel;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class MyModelTest {


    @Test
    void getLife() {
        MyModel model = new MyModel();
        assertEquals(3, model.getLife(), "Initial life should be 3");
    }

    @Test
    void createBlockRows() {
        // Erstellen Sie ein MyModel-Objekt
        MyModel model = new MyModel();

        // Überprüfen Sie die Anzahl der Blöcke vor dem Aufruf von createBlockRows
        int initialNumberOfBlocks = model.getBlocks().size();

        // Rufen Sie die createBlockRows Methode auf
        model.createBlockRows();

        // Überprüfen Sie die Anzahl der Blöcke nach dem Aufruf von createBlockRows
        int finalNumberOfBlocks = model.getBlocks().size();

        // Die erwartete Anzahl der Blöcke ist die anfängliche Anzahl plus die Anzahl der neu erstellten Blöcke
        int expectedNumberOfBlocks = initialNumberOfBlocks + 7*14;  // Ersetzen Sie dies durch die Anzahl der Blöcke, die von createBlockRows erstellt werden sollten

        assertEquals(expectedNumberOfBlocks, finalNumberOfBlocks);
    }


    @Test
    void createBlock() {
        MyModel model = new MyModel();
        int initialBlockSize = model.getBlocks().size();

        float[] blockCords = {700f, 150.0f, 6.0f};
        model.createBlock(blockCords);

        List<float[]> blocks = model.getBlocks();


        assertEquals(initialBlockSize + 1, blocks.size());


    }




    @Test
    void getScore() {

        MyModel model = new MyModel();

        // Überprüfen Sie den anfänglichen Lebenswert
        assertEquals(0, model.getScore(), "Initial Score should be 0");
    }

    @Test
    void getPlayerX() {
        MyModel model = new MyModel();

        // Überprüfen Sie, ob getPlayerX den erwarteten Wert zurückgibt
        assertEquals(370.0F, model.getPlayerX());
    }


    @Test
    void setPlayerX() {
        MyModel model = new MyModel();

        // Setzen Sie die X-Position des Spielers
        float newX = 400.0F;
        model.setPlayerX(newX);

        // Überprüfen Sie, ob setPlayerX die X-Position des Spielers korrekt gesetzt hat
        assertEquals(newX, model.getPlayerX());
    }


    @Test
    void getPlayerY() {
        MyModel model = new MyModel();

        // Überprüfen Sie, ob getPlayerX den erwarteten Wert zurückgibt
        assertEquals(570.0F, model.getPlayerY());

    }

    @Test
    void getPlayerWidth() {
        MyModel model = new MyModel();

        // Überprüfen Sie, ob getPlayerX den erwarteten Wert zurückgibt
        assertEquals(150, model.getPlayerWidth());
    }

    @Test
    void getPlayerHeight() { MyModel model = new MyModel();

        // Überprüfen Sie, ob getPlayerX den erwarteten Wert zurückgibt
        assertEquals(20, model.getPlayerHeight());
    }



    @Test
    public void moveBall() {


            MyModel model = new MyModel();



            float originalBallY = model.getBalls().getFirst()[1];


            model.moveBall();


            assertNotEquals(originalBallY, model.getBalls().getFirst()[1], "The y position of the ball did not change.");

    }


    @Test
    public void checkCollision() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Entfernen Sie alle vorhandenen Bälle
        model.getBalls().clear();

        // Fügen Sie einen Ball hinzu, der mit dem Spieler kollidieren wird
        model.getBalls().add(new float[]{model.getPlayerX(), model.getPlayerY() , 0, 5, 10});

        // Speichern Sie die ursprüngliche Geschwindigkeit des Balls in der y-Richtung
        float originalBallSpeedY = model.getBalls().get(0)[3];

        // Rufen Sie die Methode checkCollision() auf
        model.checkCollision(model.getBalls().get(0));


        // Überprüfen Sie, ob die Geschwindigkeit des Balls in der y-Richtung negativ geworden ist
        assertTrue(model.getBalls().get(0)[3] < originalBallSpeedY, "The ball did not bounce off the player.");
    }






    @Test
    void getBlocks() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie einen Block hinzu
        float[] block = new float[]{100, 100, 50, 20, 0, 0};
        model.getBlocks().add(block);

        // Rufen Sie die Methode getBlocks() auf
        List<float[]> blocks = model.getBlocks();

        // Überprüfen Sie, ob die zurückgegebene Liste den hinzugefügten Block enthält
        assertTrue(blocks.contains(block), "The list of blocks does not contain the added block.");
    }


    @Test
    void isWinning() {
        MyModel model = new MyModel();

        // Entferne alle Blöcke
        model.getBlocks().clear();

        // Überprüfe, ob das Spiel gewonnen wurde
        assertTrue(model.isWinning(), "isWinning should return true when all blocks are removed");

        // Füge einen Block hinzu
        model.getBlocks().add(new float[]{0, 0, 50f, 20f, 0f, 0f});

        // Überprüfe, ob das Spiel nicht gewonnen wurde
        assertFalse(model.isWinning(), "isWinning should return false when there are still blocks");
    }

    @Test
    void shouldDropItem() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Rufen Sie die Methode shouldDropItem() mehrmals auf und zählen Sie, wie oft sie true zurückgibt
        int trueCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (model.shouldDropItem()) {
                trueCount++;
            }
        }

        // Überprüfen Sie, ob die Methode ungefähr 20% der Zeit true zurückgibt
        assertTrue(trueCount > 150 && trueCount < 250, "The method did not return true about 20% of the time.");
    }

    @Test
    void itemCollision() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie ein Item hinzu, das den Spieler treffen wird
        model.getItems().add(new float[]{model.getPlayerX(), model.getPlayerY(), 0});
        model.getItems().add(new float[]{model.getPlayerX(), model.getPlayerY(), 1});
        model.getItems().add(new float[]{model.getPlayerX(), model.getPlayerY(), 2});

        // Speichern Sie die ursprüngliche Größe der items Liste
        int originalSize = model.getItems().size();

        // Rufen Sie die Methode itemCollision() auf
        model.itemCollision();

        // Überprüfen Sie, ob die Größe der items Liste abgenommen hat
        assertTrue(model.getItems().size() < originalSize, "The item was not removed.");
    }

    @Test
    void getItems() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie ein Item hinzu
        float[] item = new float[]{100, 100, 0};
        model.getItems().add(item);

        // Rufen Sie die Methode getItems() auf
        List<float[]> items = model.getItems();

        // Überprüfen Sie, ob die zurückgegebene Liste das hinzugefügte Item enthält
        assertTrue(items.contains(item), "The list of items does not contain the added item.");
    }

    @Test
    void getBalls() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie einen Ball hinzu
        float[] ball = new float[]{100, 100, 0, 5, 10};
        model.getBalls().add(ball);

        // Rufen Sie die Methode getBalls() auf
        List<float[]> balls = model.getBalls();

        // Überprüfen Sie, ob die zurückgegebene Liste den hinzugefügten Ball enthält
        assertTrue(balls.contains(ball), "The list of balls does not contain the added ball.");
    }

    @Test
    void getShots() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie einen Schuss hinzu
        float[] shot = new float[]{100, 100, 0, 5, 10};
        model.getShots().add(shot);

        // Rufen Sie die Methode getShots() auf
        List<float[]> shots = model.getShots();

        // Überprüfen Sie, ob die zurückgegebene Liste den hinzugefügten Schuss enthält
        assertTrue(shots.contains(shot), "The list of shots does not contain the added shot.");
    }

    @Test
    void item1() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Speichern Sie die ursprüngliche Breite des Spielers
        float originalPlayerWidth = model.getPlayerWidth();

        // Rufen Sie die Methode item1() auf
        model.item1();

        // Überprüfen Sie, ob die Breite des Spielers erhöht wurde
        assertTrue(model.getPlayerWidth() > originalPlayerWidth, "The player width was not increased.");
    }

    @Test
    void item2() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Speichern Sie die ursprüngliche Anzahl der Bälle
        int originalBallCount = model.getBalls().size();

        // Rufen Sie die Methode item2() auf
        model.item2();

        // Überprüfen Sie, ob ein neuer Ball hinzugefügt wurde
        assertTrue(model.getBalls().size() > originalBallCount, "No new ball was added.");

        // Überprüfen Sie, ob der neue Ball in der Mitte des Spielers platziert wurde
        float[] newBall = model.getBalls().get(model.getBalls().size() - 1);
        assertEquals(model.getPlayerX() + model.getPlayerWidth() / 2, newBall[0], "The new ball was not placed in the middle of the player.");
    }

    @Test
    void item3() throws InterruptedException {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Speichern Sie die ursprüngliche Anzahl der Schüsse
        int originalShotCount = model.getShots().size();

        // Rufen Sie die Methode item3() auf
        model.item3();

        // Warten Sie, um sicherzustellen, dass alle Schüsse hinzugefügt wurden
        Thread.sleep(5000);

        // Überprüfen Sie, ob 10 neue Schüsse hinzugefügt wurden
        assertEquals(originalShotCount + 10, model.getShots().size(), "The method did not add 10 new shots.");
    }

    @Test
    void moveShots() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Fügen Sie einen Schuss hinzu, der sich nach oben bewegt
        float[] shot = new float[]{100, 100, 0, -5, 10};
        model.getShots().add(shot);

        // Speichern Sie die ursprüngliche y-Position des Schusses
        float originalShotY = shot[1];

        // Rufen Sie die Methode moveShots() auf
        model.moveShots();

        // Überprüfen Sie, ob die y-Position des Schusses verringert wurde
        assertTrue(shot[1] < originalShotY, "The shot did not move upwards.");

        // Fügen Sie einen Schuss hinzu, der den oberen Rand des Spielfelds überschreiten wird
        shot = new float[]{100, 1, 0, -5, 10};
        model.getShots().add(shot);

        // Rufen Sie die Methode moveShots() erneut auf
        model.moveShots();

        // Überprüfen Sie, ob der Schuss entfernt wurde
        assertFalse(model.getShots().contains(shot), "The shot was not removed.");
    }

    @Test
    void checkShotBlockCollisions() {
        MyModel model = new MyModel();

        model.getBlocks().clear();
        // Füge einen Block hinzu
        model.getBlocks().add(new float[]{100, 100, 50f, 20f, 0f, 0f});

        // Füge einen Schuss hinzu, der den Block trifft
        model.getShots().add(new float[]{100, 100, 0, -5, 10});


        // Überprüfe die Kollisionen
        model.checkShotBlockCollisions();


        // Überprüfe, ob der Block entfernt wurde
        assertTrue(model.getBlocks().isEmpty(), "The block should be removed when hit by a shot");

        // Überprüfe, ob der Schuss entfernt wurde
        assertTrue(model.getShots().isEmpty(), "The shot should be removed when it hits a block");
    }


    @Test
    void ballOutOfBounce() {
        MyModel model = new MyModel();

        // Füge einen Ball hinzu, der sich außerhalb des Spielfelds befindet
        model.getBalls().add(new float[]{100, 620, 0, -5, 10}); // Angenommen, die Spielfeldgrenze ist bei y=610

        // Speichere die ursprüngliche Anzahl der Bälle
        int originalBallCount = model.getBalls().size();

        // Überprüfe, ob der Ball aus dem Spielfeld hinaus ist
        model.ballOutOfBounce();

        // Überprüfe, ob die Anzahl der Bälle sich verringert hat
        assertTrue(model.getBalls().size() < originalBallCount, "The number of balls should decrease when a ball goes out of bounds");
    }



    @Test
    void resetGameLoose() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Rufen Sie die Methode resetGameLoose() auf
        model.resetGameLoose();

        // Überprüfen Sie, ob das Spiel korrekt zurückgesetzt wurde
        assertEquals(3, model.getLife(), "The life was not reset to 3.");
        assertEquals(0, model.getScore(), "The score was not reset to 0.");
        assertEquals(150, model.getPlayerWidth(), "The player width was not reset to 150.");
        assertFalse(model.getBlocks().isEmpty(), "The blocks were not recreated.");
    }

    @Test
    void resetGameWin() {
        // Erstellen Sie eine Instanz Ihrer Klasse
        MyModel model = new MyModel();

        // Rufen Sie die Methode resetGameWin() auf
        model.resetGameWin();

        // Überprüfen Sie, ob das Spiel korrekt zurückgesetzt wurde
        assertFalse(model.getBlocks().isEmpty(), "The blocks were not recreated.");
        assertEquals(1, model.getBalls().size(), "A new ball was not added.");
        assertTrue(model.getItems().isEmpty(), "The items were not cleared.");
    }
}