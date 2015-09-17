package evolvingWorld.appUtils.events;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventListener;
/**
 * <p>
 * All classes which intend to use events should inherit this class. This class
 * deals with the management of EventListeners.</p>
 *
 * @author Dynisious 09/09/2015
 * @param <T> The Type of EventListener that listen on this EventObject.
 *
 * @versions 0.0.1
 */
public abstract class EventObject<T extends EventListener>
        implements GlobalEventListener {
    private final ArrayList<T> listeners = new ArrayList<>(0); //The EventListeners
    //listening for this EventObject.
    /**
     * <p>
     * Adds the passed EventListener to the list of EventListeners on this
     * EventObject.</p>
     *
     * @param l The EventListener to add.
     */
    public void addListener(final T l) throws NullPointerException {
        synchronized (listeners) {
            if (l == null) {
                throw new NullPointerException(
                        "ERROR : The passed listener was a null value.");
            }
            listeners.add(l);
        }
    }
    /**
     * <p>
     * Removes the EventListener kept at the passed index.</p>
     *
     * @param index The index of the EventListener to remove.
     */
    public void removeListener(final int index) {
        synchronized (listeners) {
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
    public void removeListener(final T l) {
        listeners.remove(l);
    }
    /**
     * <p>
     * Returns an array of all EventListeners or null if there are none.</p>
     *
     * @param cls The class type to return.
     *
     * @return The array containing all EventListeners on this EventObject.
     */
    public T[] getListeners(final Class<? extends EventListener> cls) {
        return (T[]) listeners.toArray((T[]) Array.newInstance(cls,
                listeners.size()));
    }
    /**
     * <p>
     * Returns true if there are no listeners on this EventObject.</p>
     *
     * @return true if there are no listeners on this EventObject.
     */
    public boolean noListeners() {
        return listeners.isEmpty();
    }
    /**
     * <p>
     * Removes all EventListeners.</p>
     */
    protected void clearListeners() {
        listeners.clear();
    }

    protected EventObject() {
    }

}
