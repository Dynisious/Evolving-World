package AppUtils.Events;

import java.util.ArrayList;
import java.util.EventListener;
/**
 * <p>
 * All classes which intend to use events should inherit this class. This class
 * deals with the management of EventListeners.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public abstract class EventObject {
    private ArrayList<EventListener> listeners; //The EventListeners listening
    //on this EventObject.
    /**
     * <p>
     * Adds the passed EventListener to the list of EventListeners on this
     * EventObject.</p>
     *
     * @param l The EventListener to add.
     */
    public void addListener(final EventListener l) {
        if (listeners == null) { //There is no ArrayList to add to.
            listeners = new ArrayList<>(1);
        }
        listeners.add(l);
    }
    /**
     * <p>
     * Removes the EventListener kept at the passed index.</p>
     *
     * @param index The index of the EventListener to remove.
     */
    public void removeListener(final int index) {
        if (listeners != null) {
            listeners.remove(index);
        }
    }
    /**
     * <p>
     * Removes the passed EventListener from the list of listeners on this
     * EventObject.</p>
     *
     * @param l The EventListener to remove.
     */
    public void removeListener(final EventListener l) {
        if (listeners != null) { //A Listener has been added.
            listeners.remove(l);
        }
    }
    /**
     * <p>
     * Returns an array of all EventListeners or null if there are none.</p>
     *
     * @return The array containing all EventListeners on this EventObject.
     */
    public EventListener[] getListeners() {
        if (listeners != null) {
            return listeners.toArray(new EventListener[listeners.size()]);
        }
        return null;
    }

    protected EventObject() {
    }

}
