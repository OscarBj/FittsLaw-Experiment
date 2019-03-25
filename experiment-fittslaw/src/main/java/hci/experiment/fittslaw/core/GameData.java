package hci.experiment.fittslaw.core;

import hci.experiment.fittslaw.utils.GameUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData {

    // Metadata
    public final String id;
    public final int TARGETS;
    public final int CONFS;
    public List<double[]> TARGET_CONFS;
    // Game info
    public int clickErrors;
    public long gameTime;

    GameData(String id, int targets, int confs) {
        this.id = id;
        this.TARGETS = targets;
        this.CONFS = confs;
        initData();
    }

    GameData(String id, String targets, String confs) {
        this.id = id;
        this.TARGETS = Integer.parseInt(targets);
        this.CONFS = Integer.parseInt(confs);
        initData();
    }

    private void initData() {
        TARGET_CONFS = new ArrayList<>();
        generateConfigurations();
    }

    public void generateConfigurations() {
        TARGET_CONFS.clear();
        double rad_diff = GameUtils.getTargetMaxRad() - GameUtils.getTargetMinRad();
        double dist_diff = GameUtils.getTargetMaxDist() - GameUtils.getTargetMinDist();
        Random rd = new Random();
        int tmp;
        for (int i = 0; i < CONFS; i++) {
            tmp = rd.nextInt((int) rad_diff);
            double rad = tmp + GameUtils.getTargetMinRad();
            tmp = rd.nextInt((int) dist_diff);
            double dist = tmp + GameUtils.getTargetMinDist();
            TARGET_CONFS.add(new double[]{rad, dist});
        }
    }

    public void incrementClickErrors() {
        clickErrors++;
    }

    public int getClickErrors() {
        return clickErrors;
    }

    public void setGameTime(long t) {
        gameTime = t;
    }

}
