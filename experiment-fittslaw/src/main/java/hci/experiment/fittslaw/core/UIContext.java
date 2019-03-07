package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.views.GameView;
import hci.experiment.fittslaw.views.ResultsView;
import hci.experiment.fittslaw.views.StartupView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

class UIContext {

    private final StackPane root;
    private final StartupView startupView;
    private final GameView gameView;
    private final ResultsView resultsView;
    private final GameController gameController;

    UIContext(GameController gameController) {
        this.gameController = gameController;
        root = new StackPane();

        startupView = gameController.getStartupView();
        gameView = gameController.getGameView();
        resultsView = gameController.getResultsView();
        initViews();

    }

    private void initViews() {
        root.getChildren().addAll(startupView, gameView, resultsView);
        root.setAlignment(Pos.CENTER);
        startupView.setVisible(true);
        gameView.setVisible(false);
        resultsView.setVisible(false);

        startupView.setChangeViewEvent((event) -> {
            if (gameController.isVerifiedConfiguration()) {
                startupView.setVisible(false);
                gameView.setGameData(gameController.getGameData());
                gameView.initView();
                gameView.setVisible(true);
            }
        });

        gameView.setChangeViewEvent((t) -> {
            gameView.setVisible(false);
            resultsView.setGameData((GameData) t);
            resultsView.setVisible(true);
        });

        resultsView.setPlayAgainEvent((event) -> {
            resultsView.setVisible(false);
            gameView.setVisible(false);
            startupView.setVisible(true);
        });
        
        resultsView.setSaveDataEvent((event) -> {
            gameController.saveToCSV();
        });
    }

    Parent getRoot() {
        return root;
    }
}
