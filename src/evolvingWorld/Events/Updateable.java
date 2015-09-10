package EvolvingWorld.Events;
/**
 * <p>
 * All Objects which intend to use an update function and would like to alert an
 * abstract number of other Objects when they update should extend this class
 * and make use of UpdateListeners.
 *
 * The update function of a child class should override the update function of
 * this class and call <code>super.update()</code> when they wish to alert all
 * listeners to the update.</p>
 *
 * @author Dynisious 08/09/2015
 * @version 0.1.1
 */
public abstract class Updateable extends EventObject {
    /**
     * <p>
     * Adds the passed UpdateListener to the list of UpdateListeners on this
     * EventObject.</p>
     *
     * @param ul The UpdateListener to add.
     */
    public final void addUpdateListener(final UpdateListener ul) {
        super.addListener(ul);
    }
    /**
     * <p>
     * Removes the UpdateListener kept at the passed index.</p>
     *
     * @param index The index of the UpdateListener to remove.
     */
    public final void removeUpdateListener(final int index) {
        super.removeListener(index);
    }
    /**
     * <p>
     * Removes the passed UpdateListener from the list of listeners on this
     * EventObject.</p>
     *
     * @param ul The UpdateListener to remove.
     */
    public final void removeUpdateListener(final UpdateListener ul) {
        super.removeListener(ul);
    }
    /**
     * <p>
     * Returns an array of all UpdateListeners or null if there are none.</p>
     *
     * @return The array containing all UpdateListeners on this EventObject.
     */
    public final UpdateListener[] getUpdateListeners() {
        return (UpdateListener[]) super.getListeners();
    }

    protected Updateable() {
    }

    /**
     * <p>
     * Fires all UpdateListeners on this Object.</p>
     */
    public void update() {
        for (final UpdateListener l : (UpdateListener[]) super.getListeners()) {
            l.objectUpdated(this);
        }
    }

}
