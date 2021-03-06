package evolvingWorld.appUtils.events;

import java.util.EventListener;
/**
 * <p>
 * An event listener for Updateables. Adding this event listener to an
 * Updateable will have have the object updated function fire whenever the
 * Updateable is updated.</p>
 *
 * @author Dynisious 08/09/2015
 * @version 0.1.1
 * @param <T> The type of Updateable this UpdateListener listens for.
 */
public interface UpdateListener<T extends UpdateEvent> extends EventListener {

    /**
     * <p>
     * Called when the Updateable this listener is on updates.</p>
     *
     * @param u The Updateable which just updated.
     */
    public void objectUpdated(final T u);

}
