package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.GameUtils;
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
    
    UIContext(GameController gameController, StartupView startupView, GameView gameView, ResultsView resultsView) {
        
        root = new StackPane();
        
        this.gameController = gameController;
        this.startupView = startupView;
        this.gameView = gameView;
        this.resultsView = resultsView;
        initViews();

    }

    private void initViews() {
        root.getChildren().addAll(startupView, gameView, resultsView);
        root.setAlignment(Pos.CENTER);
        startupView.setVisible(true);
        gameView.setVisible(false);
        resultsView.setVisible(false);
        
        startupView.setSelectFileEvent((event) -> {
            String path = gameController.initLogDir();
            startupView.filePathField.setText(path);
        });
        
        startupView.setChangeViewEvent((event) -> {

            if (GameUtils.isValidGameParams(startupView.nrofTargetsField.getText(), startupView.nrofConfsField.getText(), startupView.idField.getText())) {
                startupView.setVisible(false);
                gameController.setGameData(startupView.nrofTargetsField.getText(), startupView.nrofConfsField.getText(), startupView.idField.getText());
                gameController.initController();
//                gameView.initView();
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
        // TODO move to controller and save after each conf
        resultsView.setSaveDataEvent((event) -> {
            gameController.saveToCSV();
        });
    }

    Parent getRoot() {
        return root;
    }
}
