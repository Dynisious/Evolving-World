package evolvingWorld;

import evolvingWorld.appUtils.GlobalEvents;
import evolvingWorld.appUtils.Logger;
import evolvingWorld.graphical.GraphicsModule;
import evolvingWorld.worldMap.WorldMap;
import evolvingWorld.graphical.GameScreen;
import evolvingWorld.worldMap.atmosphere.AtmosphereTile;
import evolvingWorld.worldMap.atmosphere.AtmosphereTileMap;
import evolvingWorld.worldMap.geology.GeologyTile;
import evolvingWorld.worldMap.geology.GeologyTileMap;
import evolvingWorld.worldMap.MapTileConstants;
import evolvingWorld.worldMap.topSoil.TopSoilTile;
import evolvingWorld.worldMap.topSoil.TopSoilTileMap;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.ImageCapabilities;
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
    private final GameScreen screen; //The GameScreen to display the game.
    private final GraphicsModule gMod; //The GraphicsModule to handle drawing frames for the game.

    /**
     * <p>
     * Creates and returns a new instance of the Application.</p>
     *
     * @param gameMap The WorldMap that represents the game.
     * @param screen  The GameScreen Object to display the games Graphics.
     * @param gMod    The GraphicsModule used to draw the frames for the game.
     */
    public Application(final WorldMap gameMap, final GameScreen screen,
                       final GraphicsModule gMod) {
        this.gameMap = gameMap;
        GlobalEvents.instance().addListener(gameMap);
        this.screen = screen;
        GlobalEvents.instance().addListener(screen);
        this.gMod = gMod;
        GlobalEvents.instance().addListener(gMod);
        gameMap.addListener(gMod);
        gameMap.startTick();
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
        final int gThreads; //The number of graphics threads to be used.
        /*<editor-fold defaultstate="collapsed" desc="Command Line Arguments">*/ {
            long tempTickPeriod = 50;
            int tempGraphicsThreads = 1;
            for (String s : args) {
                if (s.contains("LogFile=")) {
                    Logger.setLogFile(s.split("\"")[1]);
                } else if (s.contains("GameTick=")) {
                    tempTickPeriod = Long.valueOf(s.split("=")[1]);
                } else if (s.contains("GraphicsThreads=")) {
                    tempGraphicsThreads = Integer.valueOf(s.split("=")[1]);
                } else if (s.contains("Debug=")) {
                    Logger.debugMode = Boolean.valueOf(s.split("=")[1]);
                }
            }
            tickPeriod = tempTickPeriod;
            gThreads = tempGraphicsThreads;
        } //</editor-fold>

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
                    GlobalEvents.instance().fireApplicationClosingEvent(
                            GlobalEvents.Standard_Close_Operation, true);
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("help")
                        || command[0].equalsIgnoreCase("h")
                        || command[0].equalsIgnoreCase("?")) {
                    //<editor-fold defaultstate="collapsed" desc="Help Command">
                    System.out.println("\r\nConsole Commands:\r\n"
                            + "  help/h/?    :  Displays all commands.\r\n"
                            + "  stop        :  Stops the application.\r\n"
                            + "  restart     :  Stops this instance of the application and starts\r\n"
                            + "                 a new instance.\r\n"
                            + "  launch-args :  Displays the arguments which can be used when\r\n"
                            + "                 launching the application from the commandline.\r\n"
                            + "  debug       :  Toggles whether the application is running in debug mode.");
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("restart")) {
                    //<editor-fold defaultstate="collapsed" desc="Restart Command">
                    loop = false;
                    GlobalEvents.instance().fireApplicationClosingEvent(
                            GlobalEvents.Application_Restarting, false);
                    final Thread mainThread = new Thread(() -> {
                        main(args);
                    });
                    mainThread.start();
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("launch-args")) {
                    //<editor-fold defaultstate="collapsed" desc="Launch Argument Command">
                    System.out.println("\r\nLaunch Arguments:\r\n"
                            + "  LogFile=         :  Sets the text file to write the message log to.\r\n"
                            + "                      The address must be in quotation marks \"<file>\".\r\n"
                            + "  GameTick=        :  Sets the number of milliseconds between each game\r\n"
                            + "                      tick for this instance of the game.\r\n"
                            + "  GraphicsThreads= :  Sets the number of Graphics threads to be created.\r\n"
                            + "                      More threads means more processing power to graphics.\r\n"
                            + "  Debug=           :  Sets whether the Application runs in debug mode.");
                    //</editor-fold>
                } else if (command[0].equalsIgnoreCase("debug")) {
                    //<editor-fold defaultstate="collapsed" desc="Debug Command">
                    System.out.println(Logger.debugMode = !Logger.debugMode);
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
        try {
            final GameScreen display = new GameScreen();
            display.createBufferStrategy(gThreads, new BufferCapabilities(
                    new ImageCapabilities(true), new ImageCapabilities(true),
                    BufferCapabilities.FlipContents.BACKGROUND));
            final WorldMap gameMap = initialiseGameWorld(tickPeriod);
            final Application app = new Application(gameMap, display,
                    new GraphicsModule(gThreads, display.getBufferStrategy()));
            Logger.instance().write(
                    "App has completed initialisation. Type \"help\" for console commands.",
                    1, true);
        } catch (AWTException ex) {
            Logger.instance().logWithStackTrace(
                    "ERROR : There was an error while creating the BufferStrategy for GameScreen: "
                    + ex.getMessage(), 1, true, ex.getStackTrace());
            GlobalEvents.instance().fireApplicationClosingEvent(
                    GlobalEvents.Error_In_Buffer_Strategy_Initialisation, true);
        }
    }

    /**
     * <p>
     * Creates and returns a new WorldMap.</p>
     *
     * @param tickPeriod The number of milliseconds between each world tick.
     *
     * @return The new WorldMap.
     */
    private static WorldMap initialiseGameWorld(final long tickPeriod) {
        Logger.instance().write("Initialising game world atmosphere...",
                4, true);
        final AtmosphereTileMap atmosphere;
        /*<editor-fold defaultstate="collapsed" desc="Atmosphere Layer">*/ {
            final AtmosphereTile[][] tiles = new AtmosphereTile[MapTileConstants.xWorldSize][MapTileConstants.yWorldSize];
            for (int x = 0; x < MapTileConstants.xWorldSize; x++) {
                for (int y = 0; y < MapTileConstants.yWorldSize; y++) {
                    tiles[x][y] = new AtmosphereTile(x, y, 0, 0, 0, 0, 0,
                            0, 0);
                }
            }
            atmosphere = new AtmosphereTileMap(tiles);
        } //</editor-fold>
        Logger.instance().write("Initialising game world crust...", 4,
                true);
        final GeologyTileMap crust;
        /*<editor-fold defaultstate="collapsed" desc="Geology Layer">*/ {
            final GeologyTile[][] tiles = new GeologyTile[MapTileConstants.xWorldSize][MapTileConstants.yWorldSize];
            for (int x = 0; x < MapTileConstants.xWorldSize; x++) {
                for (int y = 0; y < MapTileConstants.yWorldSize; y++) {
                    tiles[x][y] = new GeologyTile(x, y, new int[0],
                            new int[0], 0);
                }
            }
            crust = new GeologyTileMap(tiles);
        } //</editor-fold>
        Logger.instance().write("Initialising game world soils...", 4,
                true);
        final TopSoilTileMap topSoil;
        /*<editor-fold defaultstate="collapsed" desc="TopSoil Layer">*/ {
            final TopSoilTile[][] tiles = new TopSoilTile[MapTileConstants.xWorldSize][MapTileConstants.yWorldSize];
            for (int x = 0; x < MapTileConstants.xWorldSize; x++) {
                for (int y = 0; y < MapTileConstants.yWorldSize; y++) {
                    tiles[x][y] = new TopSoilTile(x, y, 0, 0, 0, 0);
                }
            }
            topSoil = new TopSoilTileMap(tiles);
        } //</editor-fold>
        return new WorldMap(atmosphere, crust, topSoil, tickPeriod);
    }

}
