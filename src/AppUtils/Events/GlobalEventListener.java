package AppUtils.Events;

import java.util.EventListener;
/**
 * <p>
 * All Objects which listen to global events should implement this
 * interface.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public interface GlobalEventListener extends EventListener {

    /**
     * <p>
     * This event fires when the application needs to exit cleanly.</p>
     *
     * @param reason This code indicates why the application is closing.
     */
    public void applicationClosing(final int reason);

}
