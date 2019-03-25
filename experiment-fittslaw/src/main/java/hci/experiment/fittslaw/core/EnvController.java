package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.FileHandler;
import hci.experiment.fittslaw.views.*;
import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;

class EnvController {

    private final UIContext uiContext;
    private final GameController gameController;
    private final FileHandler fileHandler;
    
    private final StartupView startupView;
    private final GameView gameView;
    private final ResultsView resultsView;
    
    EnvController(boolean expmode, String filePath) {
        fileHandler = new FileHandler();
        startupView = new StartupView(expmode);
        gameView = new GameView();
        resultsView = new ResultsView(expmode);
        gameController = new GameController(fileHandler,gameView);
        uiContext = new UIContext(gameController,startupView,gameView,resultsView);
        
        if(expmode){
            File file = new File(filePath + "/fittslaw_experiment");

            try {
                fileHandler.initUser(file);
            } catch (IOException ex) {
                System.out.println("error setting up exp mode..."+ex);
            }
        }
    }

    Parent getUIRoot() {
        return uiContext.getRoot();
    }

    void provideStage(Stage stage) {
        gameController.setStage(stage);
    }

}
