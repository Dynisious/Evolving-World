package EvolvingWorld;

import AppUtils.Logger;
import EvolvingWorld.Terrain.*;
/**
 * <p>
 * This is the most outward class, code should only run in here to initialise
 * and maybe finalise the application. Other than that this should only contain
 * an instance of the Game.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public final class Application {
    public static boolean applicationAlive = true; //This boolean is the big
    //red button, do not change this value unless you want the application to
    //die instantly.
    private final TerrainMap terrain; //The terrain for this instance of the game.

    public Application() {
        terrain = new TerrainMap();
        terrain.generateTerrain((long) (Math.random() * Long.MAX_VALUE));
    }

    /**
     * <p>
     * This initialises the application from the command line, taking String
     * arguments which we can interpret. This should just initialise everything
     * and then keep the application alive.</p>
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger.instance().setLogFile("Log.log"); //Default file to write the log to.
        /*<editor-fold defaultstate="collapsed" desc="Command Line Arguments">*/ {
            for (String s : args) {
                if (s.contains("LogFile=")) {
                    Logger.instance().setLogFile(s.split("\"")[1]);
                }
            }
        } //</editor-fold>

        Logger.instance().write("App is starting initialisation.", 1, true);
        Logger.instance().write("Initialising instance of the game...", 2, true);
        final Application app = new Application();
        Logger.instance().write("App has completed initialisation.", 1, true);

        while (applicationAlive) {
            try {
                Thread.currentThread().wait(3000);
            } catch (InterruptedException ex) {
                applicationAlive = false; //Get out.
            }
        } //Loop this until the application needs to get out now.
        System.exit(1); //Get out now.
    }

}
