package hci.experiment.fittslaw.core;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        EnvController env = new EnvController();
        env.provideStage(stage);
        Scene scene = new Scene(env.getUIRoot());
        
        // add styles for circles (choose color and size from the css)
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Fitts Law - Experiment");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
