package tutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TypingTutor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TypingTutor_Controller controller = new TypingTutor_Controller();



        primaryStage.setTitle("Hello World");

        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }

}
