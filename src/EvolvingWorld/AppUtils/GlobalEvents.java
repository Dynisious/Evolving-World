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
    public static final int Standard_Close_Operation = 0;
    public static final int Application_Restarting = 1;
    public static final int Error_In_Execution = 2;
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
    public synchronized void applicationClosing(final int reason,
                                                final boolean exitApplication) {
        final GlobalEventListener[] ls = getListeners(
                GlobalEventListener.class);
        if (ls != null) {
            for (final GlobalEventListener l : ls) {
                l.applicationClosing(reason);
            }
        }
        String message = "The application is closing. Reason=" + reason
                + "\r\n  Stack Trace -- " + Thread.currentThread().getName();
        for (final StackTraceElement s : Thread.currentThread().getStackTrace()) {
            message += String.format("\r\n    %-10s",
                    "Line:" + s.getLineNumber()) + "\t" + s.toString();
        }
        Logger.instance().write(message, 1, true);
        if (exitApplication) {
            System.exit(reason);
        }
    }

}
