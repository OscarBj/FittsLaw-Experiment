package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.FileHandler;
import hci.experiment.fittslaw.utils.GameUtils;
import hci.experiment.fittslaw.views.GameView;
import hci.experiment.fittslaw.views.ResultsView;
import hci.experiment.fittslaw.views.StartupView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

class GameController {

//    private final StartupView startupView;
    private final GameView gameView;
//    private final ResultsView resultsView;

    private final FileHandler fileHandler;
    private EventHandler<MouseEvent> clickEvent;
    private GameData gameData;
    private Stage stage;

    int targetIdx = 0;
    long startTime;
    long endTime;
    private double size;
    private double radius;

    GameController(FileHandler fileHandler, GameView gameView) {
        this.fileHandler = fileHandler;
//        this.startupView = startupView;
        this.gameView = gameView;
//        this.resultsView = resultsView;

//        initController();
    }

    String initLogDir() {
        String path = "";
        DirectoryChooser fc = new DirectoryChooser();

        File f = fc.showDialog(stage);
        if (f != null) {
            File file = new File(f.getPath() + "/fittslaw_experiment");

            try {
                fileHandler.initUser(file);
                path = file.getPath();
            } catch (IOException ex) {

            }
        }
        return path;
    }

    public void initController() {
        clickEvent = (event) -> {
            if (event.getSource() instanceof Circle) {
                Circle c = (Circle) event.getSource();
                if (gameView.targetList.indexOf(c) == targetIdx) {
//                    System.out.println("idx: " + gameView.targetList.indexOf(c) + " trgt: " + targetIdx);
                    targetIdx++;
                    updateTarget();
                    event.consume();
                }

            } else {
//                System.out.println("Click error detected");
                gameData.incrementClickErrors();
            }
        };
        targetIdx = 0;
        gameView.targetGroup.getChildren().clear();
        gameView.targetList.clear();

        gameView.setAlignment(Pos.CENTER);
        gameView.setMinHeight(GameUtils.getH());
        gameView.setMinWidth(GameUtils.getW());
        gameView.setOnMouseClicked(clickEvent);

        for (int i = 0; i < gameData.TARGETS; i++) {
            Circle target = new Circle();
            target.setFill(Color.BLACK);
            gameView.targetList.add(target);
            gameView.targetGroup.getChildren().add(target);
        }

        refreshTargets();

    }

    GameData setGameData(String targets, String confs, String id) {
        gameData = new GameData(id, targets, confs);
        gameView.setGameData(gameData);
        return gameData;
    }

    public void updateTarget() {

        if (targetIdx == gameData.TARGETS) {
            targetIdx = 0;
            endTime = System.currentTimeMillis();
            refreshTargets();
        }

        for (Circle c : gameView.targetList) {
            c.setFill(Color.GRAY);
        }

        Circle t = gameView.targetList.get(targetIdx);
        t.setFill(Color.RED);
        t.toFront();
    }

    void refreshTargets() {
        if (gameData.TARGET_CONFS.size() > 0) {
            if (gameData.CONFS != gameData.TARGET_CONFS.size()) {
                // user's number, the width of the target and the distance between the
                //targets, the completion time, the number of clicks errors,
                fileHandler.writeLog(gameData.TARGET_CONFS.size(),gameData.id, radius, size, startTime, endTime, gameData.clickErrors);
                gameData.clickErrors = 0;
            }
            // reset timer
            Random random = new Random();
            int index = random.nextInt(gameData.TARGET_CONFS.size());
            double[] conf = gameData.TARGET_CONFS.get(index);

            radius = conf[1];
            size = conf[0];

            gameData.TARGET_CONFS.remove(index);

            double slice = 2 * Math.PI / gameData.TARGETS;
            for (int i = 0; i < gameView.targetList.size(); i++) {
                Circle circle = gameView.targetList.get(i);
                circle.setRadius(size);
                double angle = slice * i;
                double X = radius * Math.cos(angle);
                double Y = radius * Math.sin(angle);
                circle.setCenterX(X);
                circle.setCenterY(Y);
                circle.setOnMouseClicked(clickEvent);
            }

            List<Circle> tmp1 = new ArrayList<>();
            int limit = gameView.targetList.size() / 2;

            for (int i = 0; i < limit; i++) {
                tmp1.add(gameView.targetList.get(i));
                tmp1.add(gameView.targetList.get(i + limit));
            }
            if (gameView.targetList.size() > (limit * 2)) {
                tmp1.add(gameView.targetList.get(limit * 2));
            }
            gameView.targetList = tmp1;

            updateTarget();
            startTime = System.currentTimeMillis();
        } else {
            fileHandler.writeLog(gameData.TARGET_CONFS.size(),gameData.id, radius, size, startTime, endTime, gameData.clickErrors);

//            System.out.println("Experiment was completed in " + ((endTime - startTime) / 1000) + "seconds\nNumber of click errors: " + gameData.getClickErrors());
            gameData.setGameTime(((endTime - startTime) / 1000));
            gameView.changeView.accept(gameData);
        }
    }

    void saveToCSV() {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("fittslaw_results_" + gameData.id + ".csv");
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
