package EvolvingWorld.Graphical;

import java.awt.Image;
import java.util.EventListener;
/**
 * <p>
 * Listens to a GraphicsModule and handles it's events.</p>
 *
 * @author Dynisious 13/09/2015
 * @version 0.0.1
 */
public interface GraphicsListener extends EventListener {

    /**
     * <p>
     * Handles the frame which has just been drawn.</p>
     *
     * @param frame   The Frame which was just drawn.
     * @param frameId The ID of this frame in the stack of frames being created.
     */
    public void frameDrawn(final Image frame, final long frameId);

}
