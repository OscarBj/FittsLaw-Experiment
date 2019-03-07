package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.FileHandler;
import hci.experiment.fittslaw.views.*;
import javafx.scene.Parent;
import javafx.stage.Stage;

class EnvController {

    private final UIContext uiContext;
    private final GameController gameController;
    private final FileHandler fileHandler;
    
    private final StartupView startupView;
    private final GameView gameView;
    private final ResultsView resultsView;
    EnvController() {
        fileHandler = new FileHandler();
        startupView = new StartupView();
        gameView = new GameView();
        resultsView = new ResultsView();
        gameController = new GameController(fileHandler,gameView,startupView,resultsView);
        uiContext = new UIContext(gameController);

    }

    Parent getUIRoot() {
        return uiContext.getRoot();
    }

    void provideStage(Stage stage) {
        gameController.setStage(stage);
    }

}
