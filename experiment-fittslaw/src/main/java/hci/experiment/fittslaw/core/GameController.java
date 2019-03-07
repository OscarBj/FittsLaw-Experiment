package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.FileHandler;
import hci.experiment.fittslaw.utils.GameUtils;
import hci.experiment.fittslaw.views.GameView;
import hci.experiment.fittslaw.views.ResultsView;
import hci.experiment.fittslaw.views.StartupView;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

class GameController {

    private final StartupView startupView;
    private final GameView gameView;
    private final ResultsView resultsView;

    private final FileHandler fileHandler;

    private GameData gameData;
    private Stage stage;

    GameController(FileHandler fileHandler, GameView gameView, StartupView startupView, ResultsView resultsView) {
        this.fileHandler = fileHandler;
        this.startupView = startupView;
        this.gameView = gameView;
        this.resultsView = resultsView;

        initController();
    }

    private void initController() {

    }

    StartupView getStartupView() {
        return startupView;
    }

    GameView getGameView() {
        return gameView;
    }

    ResultsView getResultsView() {
        return resultsView;
    }

    GameData getGameData() {
        int targets = Integer.parseInt(startupView.nrofTargetsField.getText());
        int configurations = Integer.parseInt(startupView.nrofConfsField.getText());
        String id = startupView.idField.getText();
        gameData = new GameData(id, targets, configurations);

        return gameData;
    }

    boolean isVerifiedConfiguration() {
        try {
            int targets = Integer.parseUnsignedInt(startupView.nrofTargetsField.getText());
            int configurations = Integer.parseUnsignedInt(startupView.nrofConfsField.getText());
            String id = startupView.idField.getText();
            return GameUtils.isValidGameParams(targets, configurations, id);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    void saveToCSV() {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("fittslaw_results_" + gameData.id+".csv");
        File file = fc.showSaveDialog(stage);
        
        if (file != null) {
            try {
                fileHandler.writeStringToFile(gameData, file);
            } catch (IOException ex) {

            }
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }
}
