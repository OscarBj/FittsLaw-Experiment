package hci.experiment.fittslaw.utils;

import hci.experiment.fittslaw.core.GameData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    
    public void writeStringToFile(GameData data, File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,CLICK ERRORS,TIME,TARGETS,CONFIGURATIONS\n");
        sb.append(data.id).append(",").append(data.clickErrors).append(",").append(data.gameTime).append(",").append(data.TARGETS).append(",").append(data.CONFS);
        
        FileOutputStream streamOut = new FileOutputStream(file);
        streamOut.write(sb.toString().getBytes());
        streamOut.close();
    }
}
