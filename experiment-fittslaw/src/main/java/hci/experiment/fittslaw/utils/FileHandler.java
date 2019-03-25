package hci.experiment.fittslaw.utils;

import hci.experiment.fittslaw.core.GameData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    AsyncFileWriter asyncFileWriter;
    String basePath;

    public FileHandler() {
        asyncFileWriter = new AsyncFileWriter();
    }

    public void initUser(File file) throws IOException {
        if (!file.exists()) {
            file.mkdir();
        }
        basePath = file.getPath();
        asyncFileWriter.setBasePath(basePath);
    }

    public void writeLog(int confid, String id, double r, double s, long st, long ed, int err) {
        asyncFileWriter.setContent(confid, id, r, s, st, ed, err);
        asyncFileWriter.run(); // this should be start to make it async
    }

    private class AsyncFileWriter extends Thread {

        private String basePath;
        private String id;
        private double target_dist;
        private double target_size;
        private int clickErrors;
        private long time;
        private int confId;

        private void setBasePath(String basePath) {
            this.basePath = basePath;
        }

        private void setContent(int confid, String id, double r, double s, long st, long ed, int err) {
            this.confId = confid;
            this.id = id;
            this.target_dist = r;
            this.target_size = s;
            this.time = ed - st;
            this.clickErrors = err;
        }

        @Override
        public void run() {
//            System.out.println("Writing file...");
            StringBuilder sb = new StringBuilder();
            sb.append("ID,CLICK ERRORS,TIME(ms),TARGET SIZE(px), TARGET DISTANCE(px),\n");
            sb.append(id).append(",").append(clickErrors).append(",").append(time).append(",").append(target_size).append(",").append(target_dist*2); // dist * 2 bc rad
            String tmpPath = "";
            FileOutputStream streamOut;
            try {
                File f = new File(basePath +"/"+ id + "_" + confId + ".csv");
                tmpPath = f.getPath();
                streamOut = new FileOutputStream(f);
                streamOut.write(sb.toString().getBytes());
                streamOut.close();
            } catch (IOException ex) {
                System.out.println("Failed to write log...\n" + ex);
            }
//            System.out.println("File saved: " + tmpPath);
            interrupt();
        }

    }

    public void writeStringToFile(GameData data, File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,CLICK ERRORS,TIME,TARGETS,CONFIGURATIONS\n");
        sb.append(data.id).append(",").append(data.clickErrors).append(",").append(data.gameTime).append(",").append(data.TARGETS).append(",").append(data.CONFS);

        FileOutputStream streamOut = new FileOutputStream(file);
        streamOut.write(sb.toString().getBytes());
        streamOut.close();
    }
}
