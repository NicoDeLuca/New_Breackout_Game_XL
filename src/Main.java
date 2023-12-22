import processing.core.*;
import View.*;
import Model.*;
import Controller.*;

public class Main {

    public static void main(String[] args) {



        var model = new MyModel();
        var view = new MyView();
        var controller = new MyController(570.0F, 330.0F,view, model);



        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);


        // Starts the processing application
        PApplet.runSketch(new String[]{"MyView"}, view);
    }
}