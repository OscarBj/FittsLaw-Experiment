package hci.experiment.fittslaw.views;

import hci.experiment.fittslaw.core.GameData;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultsView extends VBox {

    TextArea resultsArea = new TextArea("Game finished");
    HBox buttonBox = new HBox();
    Button saveButton = new Button("Save results");
    Button exitButton = new Button("Exit");
    Button playButton = new Button("Play Again");

    public ResultsView(boolean expmode) {
        if(expmode){
            resultsArea.setVisible(false);
            saveButton.setVisible(false);
        }
        initView();
    }

    private void initView() {
        setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(saveButton, playButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        getChildren().addAll(resultsArea, buttonBox);
        resultsArea.setEditable(false);

        exitButton.setOnAction((event) -> {
            System.exit(0);
        });

    }

    public void setGameData(GameData gameData) {
        StringBuilder sb = new StringBuilder("Game results:\n");
        sb.append("Id: ").append(gameData.id).append("\n");
        sb.append("Click errors: ").append(gameData.clickErrors).append("\n");
        sb.append("Time: ").append(gameData.gameTime).append(" seconds\n");
        sb.append("Target configurations: ").append(gameData.CONFS).append("\n");
        sb.append("Targets: ").append(gameData.TARGETS);
        resultsArea.setText(sb.toString());
    }

    public void setSaveDataEvent(EventHandler t) {
        saveButton.setOnAction(t);
    }

    public void setPlayAgainEvent(EventHandler t) {
        playButton.setOnAction(t);
    }
}
