package EvolvingWorld.Graphical;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Events.UpdateListener;
import EvolvingWorld.AppUtils.GlobalEvents;
import EvolvingWorld.AppUtils.Logger;
import EvolvingWorld.WorldMap.MapTileConstants;
import EvolvingWorld.WorldMap.WorldUpdateEvent;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Semaphore;
/**
 * <p>
 * This is used to generate frame by frame images to display what is going on in
 * the game world.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.0.2
 */
public final class GraphicsModule implements UpdateListener<WorldUpdateEvent>,
                                             GlobalEventListener,
                                             GraphicsListener {
    final Drawer[] drawers; //The Threads which will be rending the frames.
    final Semaphore closedThreads = new Semaphore(0); //This semaphore is used
    //while closing the drawing threads to wait till they have all closed.
    private WorldUpdateEvent frameData; //The WorldUpdateEvent to use
    final Semaphore nextFrameSignal = new Semaphore(0); //The Semaphore used to
    //signal when a new frame is ready to start being drawn.
    private long nextFrameId = 0; //The id of the next frame which gets rendered.
    public synchronized long getNextFrameId() {
        return nextFrameId++;
    }
    private long lastFrameId = -1; //The id of the last frame which was rendered.
    private final BufferStrategy strategy; //The BufferStrategy to use to render
    //game graphics.

    /**
     * <p>
     * Creates a new GraphicsModule with the specified number of buffered frames
     * which can be drawn to.</p>
     *
     * @param threads  The number of GraphicsThreads allowed to be running at
     *                 any
     *                 one time.
     * @param strategy The BufferStrategy used to double buffer the game
     *                 graphics.
     */
    public GraphicsModule(final int threads, final BufferStrategy strategy)
            throws IllegalArgumentException {
        if (threads < 1) {
            throw new IllegalArgumentException(
                    "ERROR : At least one thread must be created for drawing, threads=" + threads);
        }

        this.drawers = new Drawer[threads];
        for (int i = 0; i < threads; i++) {
            final Drawer d = new Drawer(i);
            drawers[i] = d;
            GlobalEvents.instance().addListener(d);
            d.start();
        }
        this.strategy = strategy;
    }

    @Override
    public synchronized void objectUpdated(WorldUpdateEvent u) {
        synchronized (nextFrameSignal) {
            frameData = u;
            nextFrameSignal.release(); //Aleart waiting threads that the next
            //frame is ready to be drawn.
        }
    }

    @Override
    public void applicationClosing(int reason) {
        synchronized (drawers) {
            for (final Drawer d : drawers) {
                d.executing = false;
            }
        }
    }

    @Override
    public void renderWorldUpdateEvent(WorldUpdateEvent wue)
            throws InterruptedException, NullPointerException {
        wue.readyForDraw.acquire(); //Wait till the frame is
        //ready to be drawn.
        final Graphics2D g; //The Graphics2D to draw the games
        //graphics on.
        synchronized (strategy) {
            g = (Graphics2D) strategy.getDrawGraphics();
        }
        if (g == null) { //The game screen closed.
            throw new NullPointerException(
                    "ERROR : The game screen has closed, draw graphics cannot be aquired.");
        }
        final int xDepth; //The number of tiles that fit across
        //the screen.
        final int yDepth; //The number of tiles that fit down
        //the screen.
                        /*<editor-fold defaultstate="collapsed" desc="Calculate Dimentions">*/ {
            final DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDisplayMode();
            xDepth = (int) Math.ceil((double) dm.getWidth()
                    / MapTileConstants.TileSideLength);
            yDepth = (int) Math.ceil(((double) dm.getHeight())
                    / MapTileConstants.TileSideLength);
            g.translate(-Math.floorMod(dm.getWidth(),
                    MapTileConstants.TileSideLength) / 2,
                    -Math.floorMod(dm.getHeight(),
                            MapTileConstants.TileSideLength) / 2);
            //Offset the map to evenly display all visible Tiles.
        } //</editor-fold>
        for (int i = 0; i < xDepth; i++) {
            final int x = wue.x + i;
            for (int j = 0; j < yDepth; j++) {
                final int y = wue.y + j;
                g.setColor(new Color(
                        (float) wue.world.crust.getTile(x,
                                y).getIntegrity(),
                        (float) wue.world.topSoil.getTile(x,
                                y).getFertility(),
                        (float) wue.world.atmosphere.getTile(
                                x, y).getHumidity(),
                        (float) wue.world.atmosphere.getTile(
                                x, y).getPressure()));
                g.fillRect(x * MapTileConstants.TileSideLength,
                        y * MapTileConstants.TileSideLength,
                        MapTileConstants.TileSideLength,
                        MapTileConstants.TileSideLength);
            }
        }
    }

    private final class Drawer extends Thread implements GlobalEventListener {
        public boolean executing = true; //This boolean keeps the Thread executing.

        public Drawer(final int id) {
            super("E-W: Renderer" + id);
            setDaemon(true);
        }

        @Override
        public void run() {
            Logger.instance().write("Thread successfully started.", 3, false);
            do { //Loop as long as the the drawer is executing.
                //<editor-fold defaultstate="collapsed" desc="Wait for next frame">
                try {
                    nextFrameSignal.acquire();
                } catch (InterruptedException ex) {
                    if (executing) {
                        Logger.instance().logWithStackTrace(
                                "ERROR : The thread was interrupted while waiting for the next frame to be passed.",
                                4, false, ex.getStackTrace());
                        return;
                    } else {
                        break;
                    }
                }
                //</editor-fold>
                final WorldUpdateEvent data; //The data to use to render the
                //next frame.
                //<editor-fold defaultstate="collapsed" desc="Collect frame data">
                synchronized (nextFrameSignal) {
                    data = frameData;
                }
                //</editor-fold>
                if (data != null) { //Data was aquired for this frame.
                    final long frameID = getNextFrameId(); //The ID of the frame
                    //being rendered.
                    Logger.instance().write("Currently drawing frame"
                            + frameID, 10, false);
                    try {
                        renderWorldUpdateEvent(data);

                        synchronized (strategy) {
                            if (lastFrameId < frameID) { //This is a more recent
                                //frame and should be rendered.
                                lastFrameId = frameID;
                                Logger.instance().write(
                                        "Currently rendering frame"
                                        + frameID, 10, false);
                                strategy.show(); //Display the graphics.
                            }
                        }
                    } catch (InterruptedException ex) {
                        if (executing) {
                            Logger.instance().logWithStackTrace(
                                    "ERROR : The thread was interrupted while waiting for the frame to be ready to draw.",
                                    4, false, ex.getStackTrace());
                            return;
                        } else {
                            break;
                        }
                    } catch (Exception ex) {
                        executing = false;
                        Logger.instance().logWithStackTrace(
                                "ERROR : There was an exception during graphics rendering. Thread will exit: "
                                + ex.getMessage(), 3, false, ex.getStackTrace());
                        synchronized (drawers) {
                            for (final Drawer d : drawers) {
                                if (d.executing) { //There is a drawer still executing.
                                    return;
                                }
                            }
                        }
                        Logger.instance().write(
                                "No Graphics Threads remain alive. Application closing...",
                                1, false);
                        closedThreads.release();
                        GlobalEvents.instance().fireApplicationClosingEvent(
                                GlobalEvents.No_Living_Graphics_Threads, true);
                    }
                }
            } while (executing);
            Logger.instance().write(getName()
                    + " has closed successfully.", 5, false);
            closedThreads.release(); //Alert that this Thread has closed.
        }

        @Override
        public void applicationClosing(int reason) {
            executing = false;
            if (isAlive()) { //Only wait on this thread if it is still executing.
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

}
