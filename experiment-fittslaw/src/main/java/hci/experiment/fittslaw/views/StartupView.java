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
import javafx.scene.layout.VBox;

public class StartupView extends VBox{

    Button startButton = new Button("Start experiment");
    
    Label nrLabel = new Label("Number of targets");
    Label confLabel = new Label("Number of target configurations");
    Label idLabel = new Label("Participant id");
    public TextField nrofTargetsField = new TextField("13");
    public TextField nrofConfsField = new TextField("9");
    public TextField idField = new TextField();
    public StartupView(){
        initView();
    }
    
    private void initView(){
        setMinHeight(GameUtils.getH());
        setMinWidth(GameUtils.getW());
        getChildren().addAll(nrLabel,nrofTargetsField,confLabel,nrofConfsField,idLabel,idField,startButton);
        setAlignment(Pos.CENTER);
        
    }
    
    public ObjectProperty<EventHandler<ActionEvent>> getChangeViewEvent(){
        return startButton.onActionProperty();
    }
    
    public void setChangeViewEvent(EventHandler t){
        startButton.setOnAction(t);
    }
}
