package EvolvingWorld;

import AppUtils.*;
import EvolvingWorld.WorldMap.Atmosphere.AtmosphereTile;
import EvolvingWorld.WorldMap.Atmosphere.AtmosphereTileMap;
import EvolvingWorld.WorldMap.Geology.GeologyTile;
import EvolvingWorld.WorldMap.Geology.GeologyTileMap;
import EvolvingWorld.WorldMap.TopSoil.TopSoilTile;
import EvolvingWorld.WorldMap.TopSoil.TopSoilTileMap;
import EvolvingWorld.WorldMap.WorldMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * <p>
 * This is the most outward class, code should only run in here to initialise
 * and maybe finalise the application. Other than that this should only contain
 * an instance of the Game.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.3.1
 */
public final class Application {
    private final WorldMap gameMap; //The WorldMap for the game.

    /**
     * <p>
     * Creates and returns a new instance of the Application.</p>
     *
     * @param gameMap The WorldMap that represents the game.
     */
    public Application(final WorldMap gameMap) {
        this.gameMap = gameMap;
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
        Thread.currentThread().setName("E-W: Main Thread");
        Logger.setLogFile("Log.log"); //Default file to write the log to.
        final long tickPeriod; //The number of milliseconds between game ticks.
        /*<editor-fold defaultstate="collapsed" desc="Command Line Arguments">*/ {
            long tempTickPeriod = 50;
            for (String s : args) {
                if (s.contains("LogFile=")) {
                    Logger.setLogFile(s.split("\"")[1]);
                } else if (s.contains("GameTick=")) {
                    tempTickPeriod = Long.valueOf(s.split("=")[1]);
                }
            }
            tickPeriod = tempTickPeriod;
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Clear Log File">
        try {
            try (FileWriter w = new FileWriter(
                    Logger.getLogFile(), false)) {
                w.write(""); //Clear the log file.
            } //Clear the log file.
        } catch (IOException ex) {
            System.out.println("ERROR : The Log file could not be cleared.");
        }
        //</editor-fold>

        Logger.instance().write("App is starting initialisation.", 1, true);
        Logger.instance().write("Initialising commandline input...", 2, true);
        //<editor-fold defaultstate="collapsed" desc="Commandline Input">
        final Thread commandline = new Thread(() -> {
            final Scanner input = new Scanner(System.in);
            boolean loop = true;
            do {
                final String[] command = input.nextLine().split(" "); //Gets the
                //seperated input the the command.
                if (command[0].equalsIgnoreCase("stop")) {
                    //<editor-fold defaultstate="collapsed" desc="Stop Command">
                    GlobalEvents.instance().applicationClosing(
                            GlobalEvents.Standard_Close_Operation, true);
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("help")
                        || command[0].equalsIgnoreCase("h")
                        || command[0].equalsIgnoreCase("?")) {
                    //<editor-fold defaultstate="collapsed" desc="Help Command">
                    System.out.println("\r\nConsole Commands:\r\n"
                            + "  help/h/?    :   Displays all commands.\r\n"
                            + "  stop        :   Stops the application.\r\n"
                            + "  restart     :   Stops this instance of the application and starts\r\n"
                            + "                  a new instance.\r\n"
                            + "  launch-args :   Displays the arguments which can be used when\r\n"
                            + "                  launching the application from the commandline.");
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("restart")) {
                    //<editor-fold defaultstate="collapsed" desc="Restart Command">
                    loop = false;
                    GlobalEvents.instance().applicationClosing(
                            GlobalEvents.Application_Restarting, false);
                    final Thread mainThread = new Thread(() -> {
                        main(args);
                    });
                    mainThread.start();
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("launch-args")) {
                    //<editor-fold defaultstate="collapsed" desc="Launch Argument Command">
                    System.out.println("\r\nLaunch Arguments:\r\n"
                            + "  LogFile=  :   Sets the text file to write the message log to.\r\n"
                            + "                The address must be in quotation marks \"<file>\".\r\n"
                            + "  GameTick= :   Sets the number of milliseconds between each game\r\n"
                            + "                tick for this instance of the game.");
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="Unrecognised Command">
                    System.out.println(
                            "Command not recognised, check spelling and try again.");
                    //</editor-fold>
                }
            } while (loop);
        }, "E-W: Console");
        commandline.setDaemon(false);
        commandline.start();
        //</editor-fold>
        Logger.instance().write("Initialising instance of the game...", 2, true);
        Logger.instance().write("Initialising game map...", 3, true);
        final WorldMap gameMap;
        /*<editor-fold defaultstate="collapsed" desc="World Map">*/ {
            Logger.instance().write("Initialising game world atmosphere...",
                    4, true);
            final AtmosphereTileMap atmosphere;
            /*<editor-fold defaultstate="collapsed" desc="Atmosphere Layer">*/ {
                atmosphere = new AtmosphereTileMap(new AtmosphereTile[0][0]);
            } //</editor-fold>
            Logger.instance().write("Initialising game world crust...", 4, true);
            final GeologyTileMap crust;
            /*<editor-fold defaultstate="collapsed" desc="Geology Layer">*/ {
                crust = new GeologyTileMap(new GeologyTile[0][0]);
            } //</editor-fold>
            Logger.instance().write("Initialising game world soils...", 4, true);
            final TopSoilTileMap topSoil;
            /*<editor-fold defaultstate="collapsed" desc="TopSoil Layer">*/ {
                topSoil = new TopSoilTileMap(new TopSoilTile[0][0]);
            } //</editor-fold>
            gameMap = new WorldMap(atmosphere, crust, topSoil, tickPeriod);
        } //</editor-fold>
        final Application app = new Application(gameMap);
        Logger.instance()
                .write(
                        "App has completed initialisation. Type \"help\" for console commands.",
                        1, true);
    }

}
