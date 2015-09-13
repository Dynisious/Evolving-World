package EvolvingWorld.AppUtils;

import EvolvingWorld.AppUtils.Events.EventObject;
import EvolvingWorld.AppUtils.Events.GlobalEventListener;
/**
 * <p>
 * This singleton is an EventObject which announces events that affect the
 * entire application.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public final class GlobalEvents extends EventObject<GlobalEventListener> {
    private static final GlobalEvents instance = new GlobalEvents();

    private GlobalEvents() {
    }

    /**
     * <p>
     * Returns the instance of GlobalEvents.</p>
     *
     * @return The single instance of GlobalEvents.
     */
    public static synchronized GlobalEvents instance() {
        return instance;
    }

    //<editor-fold defaultstate="collapsed" desc="Reasons to Close">
    /**
     * <p>
     * This is a standard close operation with no abnormalities.</p>
     */
    public static final int Standard_Close_Operation = 0;
    /**
     * <p>
     * The user wishes to restart the application.</p>
     */
    public static final int Application_Restarting = 1;
    /**
     * <p>
     * There was an error while executing the update loop for the game.</p>
     */
    public static final int Error_In_Game_Execution = 2;
    /**
     * <p>
     * There was an error while trying to create the BufferStrategy for
     * displaying the games graphics.</p>
     */
    public static final int Error_In_Buffer_Strategy_Initialisation = 3;
    /**
     * <p>
     * There are no graphics threads left alive to render the game.</p>
     */
    public static final int No_Living_Graphics_Threads = 4;
    /**
     * <p>
     * The GameScreen for the application has been closed.</p>
     */
    public static final int Game_Window_Closed = 5;
    //</editor-fold>
    /**
     * <p>
     * Firing this event lets all GlobalEventListeners know that the application
     * is closing and that they should finalise before calling if specified
     * exit.</p>
     *
     * @param reason          The reason that the application is closing.
     * @param exitApplication True if the exit should be called before this
     *                        method exits.
     */
    public synchronized void fireApplicationClosingEvent(final int reason,
                                                         final boolean exitApplication) {
        final GlobalEventListener[] ls = getListeners(
                GlobalEventListener.class);
        if (ls != null) {
            for (final GlobalEventListener l : ls) {
                l.applicationClosing(reason);
            }
        }
        applicationClosing(reason);
        Logger.instance().logWithStackTrace(
                "The application is closing. Reason=" + reason
                + "\r\n  Stack Trace -- " + Thread.currentThread().getName(),
                1, true, Thread.currentThread().getStackTrace());
        if (exitApplication) {
            System.exit(reason);
        }
    }
    @Override
    public void applicationClosing(int reason) {
        clearListeners();
    }

}
