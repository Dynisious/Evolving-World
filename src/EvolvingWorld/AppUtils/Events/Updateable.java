package EvolvingWorld.AppUtils.Events;
/**
 * <p>
 * All Objects which intend to use an fire UpdateEvents function and would like
 * to alert an abstract number of other Objects when they fire UpdateEvents
 * should extend this class and make use of UpdateListeners.
 *
 * A child class should call the <code>fireUpdateEvent</code> function when they
 * wish to alert all listeners to the UpdateEvent.</p>
 *
 * @author Dynisious 08/09/2015
 * @version 0.1.1
 * @param <T> The Type of UpdateEvent which is thrown by this Updateable.
 * @param <E> The Type of UpdateEvent which can cause this one.
 */
public abstract class Updateable<T extends UpdateEvent, E extends UpdateEvent>
        extends EventObject<UpdateListener<T>> {

    /**
     * <p>
     * Creates and returns a new UpdateEvent.</p>
     *
     * @return The new UpdateEvent.
     */
    protected abstract T getUpdateEvent();

    /**
     * <p>
     * Fires all UpdateListeners on this Object.</p>
     *
     * @return The UpdateEvent used in this Update.
     */
    public T fireUpdateEvent() {
        final T event = getUpdateEvent();
        if (!noListeners()) {
            for (final UpdateListener l : getListeners(UpdateListener.class)) {
                l.objectUpdated(event);
            }
        }
        return event;
    }

    /**
     * <p>
     * Creates and returns a new UpdateEvent.</p>
     *
     * @param src The UpdateEvent which caused this one.
     *
     * @return The new UpdateEvent.
     */
    protected abstract T getUpdateEvent(final E src);

    /**
     * <p>
     * Fires all UpdateListeners on this Object.</p>
     *
     * @param <E> The type of UpdateEvent which caused this one.
     * @param src The UpdateEvent which caused this one.
     *
     * @return The UpdateEvent used in this Update.
     */
    public T fireUpdateEvent(final E src) {
        final T event = getUpdateEvent(src);
        if (!noListeners()) {
            for (final UpdateListener l : getListeners(UpdateListener.class)) {
                l.objectUpdated(event);
            }
        }
        return event;
    }

}
