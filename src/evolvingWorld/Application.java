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
 * @version 0.0.1
 */
public final class Application {
    public static boolean applicationAlive = true; //This boolean is the big
    //red button, do not change this value unless you want the application to
    //die instantly.

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
            final FileWriter w = new FileWriter(
                    Logger.getLogFile(), false);
            w.write(""); //Clear the log file.
            w.close();
        } catch (IOException ex) {
            System.out.println("The Log file could not be cleared.");
        }
        //</editor-fold>

        Logger.instance().write("App is starting initialisation.", 1, true);
        Logger.instance().write("Initialising commandline input...", 2, true);
        //<editor-fold defaultstate="collapsed" desc="Commandline Input">
        final Thread commandline = new Thread(() -> {
            final Scanner input = new Scanner(System.in);
            while (applicationAlive) {
                final String[] command = input.nextLine().split(" "); //Gets the
                //seperated input the the command.
                if (command[0].equalsIgnoreCase("stop")) { //Stop the application.
                    GlobalEvents.instance().applicationClosing(
                            GlobalEvents.Standard_Close_Operation);
                } else if (command[0].equalsIgnoreCase("help")) {
                    System.out.println("Commands:\r\n"
                            + " help :\tDisplays all commands."
                            + " stop :\tStops the application.");
                } else {
                    System.out.println(
                            "Command not recognised, check spelling and try again.");
                }
            }
        }, "Evolving-World: Command Line Input");
        commandline.setDaemon(true);
        commandline.start();
        //</editor-fold>
        Logger.instance().write("Initialising instance of the game...", 2, true);
        final Application app = new Application();
        Logger.instance().write("App has completed initialisation.", 1, true);

        while (applicationAlive) {
            try {
                synchronized (app) {
                    app.wait(5000);
                }
            } catch (InterruptedException ex) {
                applicationAlive = false; //Get out.
            }
        } //Loop this until the application needs to get out now.
        Logger.instance().write("App is jumping out!", 1, true);
        System.exit(GlobalEvents.Big_Red_Button); //Get out now.
    }

}
