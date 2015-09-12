package EvolvingWorld.AppUtils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * The Logger is our output tool. we can write Strings to a log text file
 * here.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.2.1
 */
public final class Logger {
    private static final Logger instance = new Logger(); //This is the one and
    //only instance of the Logger.
    private static String logFile; //The text file to write to.
    private final static Object logFileLock = new Object(); //The Object used to
    //lock access to the LogFile.
    /**
     * <p>
     * Sets the file that the logger writes to.</p>
     *
     * @param logFile The String representing the address of the log file.
     */
    public static void setLogFile(final String logFile) {
        synchronized (logFileLock) {
            try {
                try (FileWriter w = new FileWriter(logFile, false)) {
                    w.write(""); //Clear the log file.
                } //Clear the log file.
            } catch (IOException ex) {
                System.out.println("ERROR : The Log file could not be cleared.");
            }
            Logger.logFile = logFile;
        }
    }
    public static boolean debugMode = false; //If true all writes to the log are
    //outputed to the console.

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

    private static final String logWithStackTraceFormat = "\r\n    Line:%-14s";
    //The format for a StackTraceElement of an error.
    /**
     * <p>
     * Formats and returns a String containing the message and the
     * StackTraceElements of an error.</p>
     *
     * @param message The message to head the error.
     * @param trace   The stack trace for the error.
     *
     * @return The formated error as a String.
     */
    private String formatError(String message, final StackTraceElement[] trace) {
        for (final StackTraceElement s : trace) {
            message += String.format(logWithStackTraceFormat, s.getLineNumber()) + s.toString();
        }
        return message;
    }

    private static final String logFormat = "%-26s"; //The format for the header
    //to a written message.
    private static final String errorMessage = "ERROR: Logger has failed! Message not saved to log: ";
    /**
     * <p>
     * This writes the past message to the log file, the importance value is
     * used to identify the criticality of the following message with '1' being
     * the highest priority and the higher the level the less important the
     * message.</p>
     *
     * @param message         The actual message to append to the log file.
     * @param importance      The criticality of the message with '1' being the
     *                        most
     *                        critical and each ascending number being less critical.
     * @param outputToConsole If this value is true the message is written to
     *                        the
     *                        console as well as the log file.
     */
    public void write(String message, final int importance,
                      final boolean outputToConsole) {
        synchronized (logFileLock) {
            message = String.format(logFormat, Thread.currentThread().getName()
                    + ":LEVEL-" + importance + ": ") + message + "\r\n";
            try (final FileWriter w = new FileWriter(logFile, true)) {
                w.write(message);
            } catch (IOException ex) {
                System.out.println(formatError(errorMessage + message,
                        ex.getStackTrace()));
            }
            if (outputToConsole || debugMode) {
                System.out.print(message);
            }
        }
    }

    /**
     * <p>
     * Logs an error with the passed message and stack trace.</p>
     *
     * @param message         The message to head the error.
     * @param importance      The importance of this error.
     * @param outputToConsole determines whether the error is printed to the
     *                        console.
     * @param trace           The stack trace for this error.
     */
    public void logWithStackTrace(final String message, final int importance,
                                  final boolean outputToConsole,
                                  final StackTraceElement[] trace) {
        synchronized (logFileLock) {
            Logger.instance().write(formatError(message, trace), importance,
                    outputToConsole);
        }
    }

}
