package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.EventObject;
import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Events.UpdateListener;
import EvolvingWorld.AppUtils.GlobalEvents;
import EvolvingWorld.AppUtils.Logger;
import EvolvingWorld.WorldMap.WorldUpdateEvent;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;
/**
 * <p>
 * This is used to generate frame by frame images to display what is going on in
 * the game world.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.0.2
 */
public final class GraphicsModule extends EventObject<GraphicsListener>
        implements UpdateListener<WorldUpdateEvent> {
    final Thread[] renderingThread; //The Threads which will be rending the frames.
    final Semaphore closedThreads = new Semaphore(0); //This semaphore is used
    //while closing the drawing threads to wait till they have all closed.
    final Object frameDataLock = new Object(); //The Object used to lock access
    //to frameData.
    private WorldUpdateEvent frameData; //The WorldUpdateEvent to use
    final Semaphore nextDrawSignal = new Semaphore(0); //The Semaphore used to
    //signal when a new frame is ready to start being drawn.
    private long nextFrameId = 0; //The id of the next frame which gets rendered.
    public synchronized long getNextFrameId() {
        return nextFrameId++;
    }

    /**
     * <p>
     * Creates a new GraphicsModule with the specified number of buffered frames
     * which can be drawn to.</p>
     *
     * @param threads The number of GraphicsThreads allowed to be running at any
     *                one time.
     * @param display The GraphicsListener which will display the drawn frames.
     */
    public GraphicsModule(final int threads, final GraphicsListener display)
            throws IllegalArgumentException {
        if (threads < 1) {
            throw new IllegalArgumentException(
                    "ERROR : At least one thread must be created for drawing, threads=" + threads);
        }

        final class Drawer extends Thread implements GlobalEventListener {
            private boolean alive = true; //This boolean keeps the Thread alive.

            public Drawer(final int id) {
                super("E-W: Frame renderer" + id);
                setDaemon(true);
            }

            @Override
            public void run() {
                do { //Loop as long as the the drawer is alive.
                    //<editor-fold defaultstate="collapsed" desc="Wait for next frame">
                    try {
                        nextDrawSignal.acquire();
                    } catch (InterruptedException ex) {
                        if (alive) {
                            Logger.instance().logWithStackTrace(
                                    "ERROR : The thread was interrupted while waiting for the next frame to be ready.",
                                    4, false, ex.getStackTrace());
                            return;
                        } else {
                            break;
                        }
                    }
                    //</editor-fold>
                    final WorldUpdateEvent data; //The data to use to render the
                    //next frame.
                    final long frameID; //The ID of the frame being rendered.
                    //<editor-fold defaultstate="collapsed" desc="Collect frame data">
                    synchronized (frameDataLock) {
                        data = frameData;
                        if (data != null) {
                            frameID = getNextFrameId();
                            frameData = null;
                        } else {
                            frameID = -1;
                        }
                    }
                    //</editor-fold>
                    if (data != null) { //Data was aquired for this frame.
                        //<editor-fold defaultstate="collapsed" desc="Draw frame">
                        final BufferedImage frame; //The Image object to draw on.
                        {
                            final DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                    .getDefaultScreenDevice().getDisplayMode();
                            frame = new BufferedImage(dm.getWidth(),
                                    dm.getHeight(),
                                    BufferedImage.TYPE_4BYTE_ABGR);
                        }
                        final Graphics2D g = frame.createGraphics(); //The graphics object to use for drawing.
                        g.setBackground(Color.BLACK);
                        g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
                        //</editor-fold>

                        fireFrameDrawnEvent(frame, frameID);
                    }

                } while (alive);
                Logger.instance().write(getName()
                        + " has closed successfully.", 5, false);
                closedThreads.release(); //Alert that this Thread has closed.
            }

            @Override
            public void applicationClosing(int reason) {
                alive = false;
                if (isAlive()) { //Only wait on this thread if it is still alive.
                    interrupt();
                    try {
                        closedThreads.acquire(); //Wait for the drawing thread to
                        //exit before continuing.
                    } catch (InterruptedException ex) {
                        Logger.instance().logWithStackTrace(
                                "ERROR : The thread was interrupted while waiting for "
                                + getName() + " to exit.", 4, false,
                                ex.getStackTrace());
                    }
                }
            }

        }

        this.renderingThread = new Drawer[threads];
        for (int i = 0; i < threads; i++) {
            final Drawer d = new Drawer(i);
            renderingThread[i] = new Drawer(i);
            GlobalEvents.instance().addListener(d);
            addListener(display);
            d.start();
        }
    }

    /**
     * <p>
     * Alerts all GraphicsListeners that a new frame has been drawn.</p>
     *
     * @param frame The frame which was just drawn.
     * @param id    The id of the frame in the stack of drawn frames.
     */
    private synchronized void fireFrameDrawnEvent(
            final Image frame, final long id) {
        for (final GraphicsListener l : getListeners(GameScreen.class)) {
            l.frameDrawn(frame, nextFrameId);
        }
    }

    @Override
    public synchronized void objectUpdated(WorldUpdateEvent u) {
        synchronized (frameDataLock) {
            frameData = u;
            nextDrawSignal.release(); //Aleart waiting threads that the next
            //frame is ready to be drawn.
        }
    }

    @Override
    public void applicationClosing(int reason) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
