package evolvingWorld;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * The Logger is our output tool. we can write Strings to a log text file
 * here.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public final class Logger {
    private static Logger instance; //This is the one and only instance
    //of the Logger.
    public static String logFile; //The text file to write to.
    private static final String errorMessage = "ERROR: Logger has failed! Message not saved to log: ";

    /**
     * <p>
     * Only Logger is able to create an instance of logger.</p>
     */
    private Logger() {
    }

    /**
     * <p>
     * Get the instance of the Logger for this instance of the application.</p>
     *
     * @return The Logger.
     */
    public static Logger Logger() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * <p>
     * This writes the past message to the log file, the importance value is
     * used to identify the criticality of the following message with '1' being
     * the highest priority and the higher the level the less important the
     * message.</p>
     *
     * @param message      The actual message to append to the log file.
     * @param importance   The criticality of the message with '1' being the
     *                     most
     *                     critical and each ascending number being less critical.
     * @param outputToUser If this value is true the message is written to the
     *                     console as well as the log file.
     */
    public void write(final String message, final int importance,
                      final boolean outputToUser) {
        try {
            final FileWriter w = new FileWriter(logFile, true);
            w.write("LEVEL-" + importance + ": " + message + "\r\n");
        } catch (IOException ex) {
            System.out.println(
                    errorMessage + message + "\r\n" + ex.toString() + "\r\n"
                    + ex.getStackTrace().toString());
        }
        if (outputToUser) {
            System.out.println("LEVEL-" + importance + ": " + message);
        }
    }

}
