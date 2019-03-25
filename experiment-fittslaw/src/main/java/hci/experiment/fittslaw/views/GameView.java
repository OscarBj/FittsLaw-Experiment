package hci.experiment.fittslaw.views;

import hci.experiment.fittslaw.core.GameData;
import hci.experiment.fittslaw.utils.GameUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameView extends VBox {

    public List<Circle> targetList = new ArrayList<>();
    public Group targetGroup = new Group();

    public Consumer<GameData> changeView;
    private GameData gameData;

    public GameView() {
        getChildren().add(targetGroup);
        managedProperty().bind(visibleProperty());

//        initView();
    }
    
    public void setClickEvent(EventHandler t){
        
    }
    
    public void setGameData(GameData data) {
        this.gameData = data;
    }

//    public void initView() {
//        targetIdx = 0;
//        targetGroup.getChildren().clear();
//        targetList.clear();
//        
//        setAlignment(Pos.CENTER);
//        setMinHeight(GameUtils.getH());
//        setMinWidth(GameUtils.getW());
//        setOnMouseClicked(clickEvent);
//
//        for (int i = 0; i < gameData.TARGETS; i++) {
//            Circle target = new Circle();
//            target.setFill(Color.BLACK);
//            targetList.add(target);
//            targetGroup.getChildren().add(target);
//        }
//
//        refreshTargets();
//
//        startTime = System.currentTimeMillis();
//    }

//    private void refreshTargets() {
//        if (gameData.TARGET_CONFS.size() > 0) {
//            Random random = new Random();
//            int index = random.nextInt(gameData.TARGET_CONFS.size());
//            double[] conf = gameData.TARGET_CONFS.get(index);
//
//            double radius = conf[1];
//            double size = conf[0];
//            gameData.TARGET_CONFS.remove(index);
//
//            double slice = 2 * Math.PI / gameData.TARGETS;
//            for (int i = 0; i < targetList.size(); i++) {
//                Circle circle = targetList.get(i);
//                circle.setRadius(size);
//                double angle = slice * i;
//                double X = radius * Math.cos(angle);
//                double Y = radius * Math.sin(angle);
//                circle.setCenterX(X);
//                circle.setCenterY(Y);
//                circle.setOnMouseClicked(clickEvent);
//            }
//
//            List<Circle> tmp1 = new ArrayList<>();
//            int limit = targetList.size() / 2;
//
//            for (int i = 0; i < limit; i++) {
//                tmp1.add(targetList.get(i));
//                tmp1.add(targetList.get(i + limit));
//            }
//            if (targetList.size() > (limit * 2)) {
//                tmp1.add(targetList.get(limit * 2));
//            }
//            targetList = tmp1;
//
//            updateTarget();
//        } else {
//            endTime = System.currentTimeMillis();
//            System.out.println("Experiment was completed in " + ((endTime - startTime) / 1000) + "seconds\nNumber of click errors: " + gameData.getClickErrors());
//            gameData.setGameTime(((endTime - startTime) / 1000));
//            changeView.accept(gameData);
//        }
//    }

//    long startTime;
//    long endTime;
//    int targetIdx = 0;

//    private final EventHandler<MouseEvent> clickEvent = (event) -> {
//        if (event.getSource() instanceof Circle) {
//            Circle c = (Circle) event.getSource();
//            if (targetList.indexOf(c) == targetIdx) {
//                System.err.println("idx: " + targetList.indexOf(c) + " trgt: " + targetIdx);
//                targetIdx++;
//                updateTarget();
//                event.consume();
//            }
//
//        } else {
//            System.out.println("Click error detected");
//            gameData.incrementClickErrors();
//        }
//    };

//    public void updateTarget() {
//
//        if (targetIdx == gameData.TARGETS) {
//            targetIdx = 0;
//            refreshTargets();
//        }
//
//        for (Circle c : targetList) {
//            c.setFill(Color.GRAY);
//        }
//
//        Circle t = targetList.get(targetIdx);
//        t.setFill(Color.RED);
//        t.toFront();
//    }

    public void setChangeViewEvent(Consumer s) {
        changeView = s;
    }

}
