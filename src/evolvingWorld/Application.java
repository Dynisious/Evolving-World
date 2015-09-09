package EvolvingWorld;

import AppUtils.GlobalEvents;
import AppUtils.Logger;
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

    /**
     * <p>
     * Creates and returns a new instance of the Application.</p>
     */
    public Application() {

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
        /*<editor-fold defaultstate="collapsed" desc="Command Line Arguments">*/ {
            for (String s : args) {
                if (s.contains("LogFile=")) {
                    Logger.setLogFile(s.split("\"")[1]);
                }
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Clear Log File">
        try {
            try (FileWriter w = new FileWriter(
                    Logger.getLogFile(), false)) {
                w.write(""); //Clear the log file.
            } //Clear the log file.
        } catch (IOException ex) {
            System.out.println("The Log file could not be cleared.");
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
                    System.out.println("Console Commands:\r\n"
                            + " help/h/? :\tDisplays all commands.\r\n"
                            + " stop     :\tStops the application.\r\n"
                            + " restart  :\tStops this instance of the application and starts a new instance.");
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
        final Application app = new Application();
        Logger.instance().write(
                "App has completed initialisation. Type \"help\" for console commands.",
                1, true);
    }

}
