package EvolvingWorld;

import java.util.ArrayList;
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
 * @version 0.0.1
 */
public abstract class Updateable {
    private ArrayList<UpdateListener> listeners; //The UpdateListeners listening
    //on this Updateable.
    public final void addUpdateListener(final UpdateListener ul) {
        if (listeners == null) { //There is no ArrayList to add to.
            listeners = new ArrayList<>(1);
        }
        listeners.add(ul);
    }
    public final void removeUpdateListener(final int index) {
        if (listeners != null) {
            listeners.remove(index);
        }
    }
    public final void removeUpdateListener(final UpdateListener ul) {
        if (listeners != null) { //A Listener has been added.
            listeners.remove(ul);
        }
    }
    public final UpdateListener[] getUpdateListeners() {
        if (listeners != null) {
            return listeners.toArray(new UpdateListener[listeners.size()]);
        }
        return null;
    }

    /**
     * <p>
     * Fires all UpdateListeners on this Object.</p>
     */
    public void update() {
        for (UpdateListener l : listeners) {
            l.objectUpdated(this);
        }
    }

}
