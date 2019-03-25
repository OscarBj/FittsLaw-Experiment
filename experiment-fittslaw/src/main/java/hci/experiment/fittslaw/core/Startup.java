package hci.experiment.fittslaw.core;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {
    static boolean expmode = false;
    static String path = "";
    @Override
    public void start(Stage stage) throws Exception {
        EnvController env = new EnvController(expmode,path);
        env.provideStage(stage);
        Scene scene = new Scene(env.getUIRoot());

        // add styles for circles (choose color and size from the css)
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Fitts Law - Experiment");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            if (args[0].equals("-experimentmode")) {
                expmode = true;
                path = args[1];
                System.out.println("Launching application in experiment mode..");
                // TODO disable conf page, display consent form
            }
        } else {
            System.out.println("Launching application in dev mode..");
        }
        launch(args);
    }

}
