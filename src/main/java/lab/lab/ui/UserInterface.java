package lab.lab.ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var pane = new BorderPane();
        var input = new TextField();
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}