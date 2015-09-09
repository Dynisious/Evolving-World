package EvolvingWorld.Graphical;

import java.awt.Image;
/**
 * <p>
 * This is used to generate frame by frame images to display what is going on in
 * the game world.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.0.2
 */
public final class GraphicsModule {
    private Image[] iBuffer; //The array of Images that can be drawn on.
    private int curFrame = -1; //The position of the current frame being
    //displayed in iBuffer.
    private int curDraw = -1; //The position of the next frame being drawn on
    //in iBuffer.

    /**
     * <p>
     * Creates a new GraphicsModule with the specified number of buffered frames
     * which can be drawn to.
     *
     * @param frames The number of buffered frames for this GraphicsModule.
     */
    public GraphicsModule(final int frames) {
        iBuffer = new Image[frames + 1];
    }

    /**
     * <p>
     * Returns the next Image which can be drawn on.</p>
     *
     * @return The Image to be drawn on.
     */
    public synchronized Image getNextDrawable() {
        synchronized (iBuffer) { //Lock access to IBuffer.
            if (++curDraw == curFrame) { //Do not draw on the frame currently being
                //displayed.
                curFrame++;
                if (curFrame >= iBuffer.length) { //Keep the index within the bounds.
                    curFrame = 0;
                }
            } else if (curDraw >= iBuffer.length) { //Keep the index within the bounds.
                curDraw = 0;
            }
            return iBuffer[curDraw];
        }
    }

    /**
     * <p>
     * Returns the next Image to be displayed.</p>
     *
     * @return The Image to be displayed.
     */
    public synchronized Image getNextFrame() {
        synchronized (iBuffer) { //Lock access to IBuffer.
            if (++curFrame >= iBuffer.length) { //Keep the index within the bounds.
                curFrame = 0;
            }
            if (curFrame == curDraw) { //Go to the next frame.
                curFrame++;
            }
            return iBuffer[curFrame];
        }
    }

}
