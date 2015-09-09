package AppUtils;

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
    private static final Logger instance = new Logger(); //This is the one and
    //only instance of the Logger.
    private static String logFile; //The text file to write to.
    /**
     * <p>
     * Sets the file that the logger writes to.</p>
     *
     * @param logFile The String representing the address of the log file.
     */
    public static void setLogFile(final String logFile) {
        synchronized (logFile) {
            Logger.logFile = logFile;
        }
    }
    /**
     * <p>
     * Gets the file that the logger writes to.</p>
     *
     * @return The String representing the address of the log file.
     */
    public static String getLogFile() {
        synchronized (logFile) {
            return logFile;
        }
    }

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
    public static Logger instance() {
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
            w.close();
        } catch (IOException ex) {
            String str = errorMessage + message + "\r\n" + ex.getMessage();
            for (StackTraceElement s : ex.getStackTrace()) {
                str += "\r\n" + s.toString();
            }
            System.out.println(str);
        }
        if (outputToUser) {
            System.out.println("LEVEL-" + importance + ": " + message);
        }
    }

}
