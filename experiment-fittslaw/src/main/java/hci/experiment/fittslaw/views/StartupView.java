package hci.experiment.fittslaw.views;

import hci.experiment.fittslaw.utils.GameUtils;
import java.util.function.Consumer;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartupView extends VBox {

    Button startButton = new Button("Start experiment");
    Button filePathButton = new Button("Select log path");

    Label nrLabel = new Label("Number of targets");
    Label confLabel = new Label("Number of target configurations");
    Label idLabel = new Label("Participant id");
    Label filePathLabel = new Label("Log file path");
    public TextField nrofTargetsField = new TextField("13");
    public TextField nrofConfsField = new TextField("9");
    public TextField idField = new TextField();
    public TextField filePathField = new TextField();

    public StartupView(boolean expmode) {
        if (expmode) {
            nrLabel.setVisible(false);
            confLabel.setVisible(false);
            filePathLabel.setVisible(false);
            nrofTargetsField.setVisible(false);
            nrofConfsField.setVisible(false);
            filePathField.setVisible(false);
            filePathButton.setVisible(false);
            startButton.disableProperty().bind(idField.textProperty().isNull().or(idField.textProperty().isEmpty()));
        } else {
            startButton.disableProperty().bind(filePathField.textProperty().isNull().or(filePathField.textProperty().isEmpty()).or(nrofConfsField.textProperty().isNull()).or(nrofConfsField.textProperty().isEmpty()).or(nrofTargetsField.textProperty().isNull()).or(nrofTargetsField.textProperty().isEmpty()).or(idField.textProperty().isNull()).or(idField.textProperty().isEmpty()));
        }
        initView();
    }

    private void initView() {
        setMinHeight(GameUtils.getH());
        setMinWidth(GameUtils.getW());

        filePathField.setDisable(true);
        getChildren().addAll(nrLabel, nrofTargetsField, confLabel, nrofConfsField, idLabel, idField, filePathLabel, filePathField, filePathButton, startButton);
        setAlignment(Pos.CENTER);
        idField.setMaxWidth(50);
//        idField.setMaxWidth(100);
    }

    public void setSelectFileEvent(EventHandler t) {
        filePathButton.setOnAction(t);
    }

    public void setChangeViewEvent(EventHandler t) {
        startButton.setOnAction(t);
    }
}
