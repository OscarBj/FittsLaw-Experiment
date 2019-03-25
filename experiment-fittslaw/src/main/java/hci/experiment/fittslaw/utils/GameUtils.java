package hci.experiment.fittslaw.utils;

/**
 *
 * @author Lenovo
 */
public class GameUtils {

    public static boolean isValidGameParams(String targets_s, String configurations_s, String id) {
        int targets = 0;
        int configurations = 0;
        try {
            targets = Integer.parseUnsignedInt(targets_s);
            configurations = Integer.parseUnsignedInt(configurations_s);
        } catch (NumberFormatException ex) {
            return false;
        }
        if (id.isEmpty() || id.equals("")) {
            return false;
        }
        if (targets < 2) {
            return false;
        }
        if (configurations < 2) {
            return false;
        } else {
            return true;
        }
    }

    public static double getH() {
        return 500.0;
    }

    public static double getW() {
        return 500.0;
    }

    public static double getTargetMinRad() {
        return 10.0;
    }

    public static double getTargetMaxRad() {
        return 50.0;
    }

    public static double getTargetMinDist() {
        return 100.0;
    }

    public static double getTargetMaxDist() {
        return 200.0;
    }

}
