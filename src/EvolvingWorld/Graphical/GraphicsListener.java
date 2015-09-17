package EvolvingWorld.Graphical;

import EvolvingWorld.WorldMap.WorldUpdateEvent;
/**
 * <p>
 * Handles the graphical events in a GraphicsModule.</p>
 *
 * @author Dynisious 17/09/2015
 * @version 0.0.1
 */
public interface GraphicsListener {

    /**
     * <p>
     * Handles the drawing of a WorldUpdateEvent.</p>
     *
     * @param wue The WorldUpdateEvent to render.
     *
     * @throws java.lang.InterruptedException Thrown if the thread is
     *                                        interrupted while waiting to draw.
     */
    public void renderWorldUpdateEvent(final WorldUpdateEvent wue)
            throws InterruptedException, NullPointerException;

}
