package slidingpuzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Al-Hussein
 */
public class SlidingPuzzle extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        GridPane grid = new GridPane();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
