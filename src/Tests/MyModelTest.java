package Tests;
import Model.MyModel;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MyModelTest {


    @Test
    void testGetLife() {
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

        float[] blockCords = {732f, 160.0f, 6.0f};   //nochmal gucken was hier abgeht!!
        model.createBlock(blockCords);

        List<float[]> blocks = model.getBlocks();

        // Überprüfen Sie, ob ein Block hinzugefügt wurde
        assertEquals(initialBlockSize + 1, blocks.size());

        // Überprüfen Sie die Eigenschaften des hinzugefügten Blocks
        float[] block = blocks.get(blocks.size() - 1);
        assertEquals(blockCords[0], block[0]);
        assertEquals(blockCords[1], block[1]);
        assertEquals(50f, block[2]);
        assertEquals(20f, block[3]);
        assertEquals(0f, block[4]);
        assertEquals(blockCords[2], block[5]);
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
    void getItemY() {
        MyModel model = new MyModel();

        // Fügt ein Item hinzu
        float[] itemCords = {10.0f, 20.0f, 3.0f};
        model.getItems().add(itemCords);

        // Überprüft, ob das zuletzt hinzugefügte Item die erwartete Y-Position hat
        float[] lastItem = model.getItems().get(model.getItems().size() - 1);
        assertEquals(itemCords[1], lastItem[1]);
    }


    @Test
    public void moveBall() {

            // Erstellen Sie eine Instanz Ihrer Klasse
            MyModel model = new MyModel();

            // Speichern Sie die ursprünglichen Werte für die Überprüfung

            float originalBallY = model.getBalls().getFirst()[1];

            // Rufen Sie die Methode moveBall() auf
            model.moveBall();

            // Fügen Sie hier Assertions hinzu, um das Verhalten von moveBall() zu überprüfen
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
    public void testCheckBlockCollisions() {

    }



    @org.junit.jupiter.api.Test
    void getBlocks() {
    }

    @org.junit.jupiter.api.Test
    void isWinning() {
    }

    @org.junit.jupiter.api.Test
    void shouldDropItem() {
    }

    @org.junit.jupiter.api.Test
    void itemCollision() {
    }

    @org.junit.jupiter.api.Test
    void getItems() {
    }

    @org.junit.jupiter.api.Test
    void getBalls() {
    }

    @org.junit.jupiter.api.Test
    void getShots() {
    }

    @org.junit.jupiter.api.Test
    void item1() {
    }

    @org.junit.jupiter.api.Test
    void item2() {
    }

    @org.junit.jupiter.api.Test
    void item3() {
    }

    @org.junit.jupiter.api.Test
    void moveShots() {
    }

    @org.junit.jupiter.api.Test
    void checkShotBlockCollisions() {
    }

    @org.junit.jupiter.api.Test
    void ballOutOfBounce() {
    }

    @org.junit.jupiter.api.Test
    void resetGameLoose() {
    }

    @org.junit.jupiter.api.Test
    void resetGameWin() {
    }
}