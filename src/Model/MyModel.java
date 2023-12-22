package Model;

public class MyModel  {

    public float ballX;
    public float ballY;
    public float ballSpeedX;
    public float ballSpeedY;

    public MyModel() {
        ballX = 400;
        ballY = 300;
        ballSpeedX = 2;
        ballSpeedY = 2;
    }



    public void moveBall() { //lässt den Ball sich bewegen
        ballX += ballSpeedX;
        ballY += ballSpeedY;
    }


}

