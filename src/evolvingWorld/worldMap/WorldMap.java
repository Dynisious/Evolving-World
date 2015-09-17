package evolvingWorld.worldMap;

import evolvingWorld.appUtils.events.GlobalEventListener;
import evolvingWorld.appUtils.events.UpdateEvent;
import evolvingWorld.appUtils.events.Updateable;
import evolvingWorld.appUtils.GlobalEvents;
import evolvingWorld.appUtils.Logger;
import evolvingWorld.worldMap.atmosphere.AtmosphereTileMap;
import evolvingWorld.worldMap.geology.GeologyTileMap;
import evolvingWorld.worldMap.topSoil.TopSoilTileMap;
import java.util.Timer;
import java.util.TimerTask;
/**
 * <p>
 * This class pulls together all the parts of the game. It runs the game tick
 * and holds all objects in the game.</p>
 *
 * @author Dynisious 12/09/2015
 * @versions 0.0.1
 */
public final class WorldMap extends Updateable<WorldUpdateEvent, UpdateEvent>
        implements GlobalEventListener {
    public final AtmosphereTileMap atmosphere; //The atmosphere of the world.
    public final GeologyTileMap crust; //The world's crust.
    public final TopSoilTileMap topSoil; //The world's top soil.
    private Timer worldTick; //The Timer object for the game.
    private final long tickPeriod; //The number of milliseconds between each update tick.
    private final TimerTask task; //The TimerTask executed each update tick.
    public void startTick() {
        if (worldTick == null) {
            worldTick = new Timer("E-W: World Tick", true);
            worldTick.scheduleAtFixedRate(task, 0, tickPeriod);
            Logger.instance().write("Game tick has been started successfully.",
                    7, false);
        }
    }
    public void stopTick() {
        if (worldTick != null) {
            synchronized (worldTick) {
                worldTick.cancel();
                worldTick = null;
                Logger.instance().write(
                        "Game tick has been stopped successfully.",
                        7, false);
            }
        }
    }
    private double viewX; //The x coordinate of the top leftmost Tile in view.
    private double viewY; //The y coordinate of the top leftmost Tile in view.

    /**
     * <p>
     * Creates and returns a new WorldMap with the passed values.</p>
     *
     * @param atmosphere The atmosphere of the world.
     * @param crust      The crust of the world.
     * @param topSoil    The top soil of the world.
     * @param tickPeriod The number of milliseconds between each game
     *                   tick.
     */
    public WorldMap(final AtmosphereTileMap atmosphere,
                    final GeologyTileMap crust, final TopSoilTileMap topSoil,
                    final long tickPeriod) {
        this.atmosphere = atmosphere;
        addListener(atmosphere);
        this.crust = crust;
        addListener(crust);
        this.topSoil = topSoil;
        addListener(topSoil);
        this.tickPeriod = tickPeriod;
        task = new TimerTask() {
            @Override
            public void run() {
                try {
                    fireUpdateEvent(null);
                } catch (Exception ex) {
                    Logger.instance().logWithStackTrace(
                            "ERROR : There was an error during game execution. "
                            + ex.getClass().getName() + ": " + ex.getMessage(),
                            1, true, ex.getStackTrace());
                    GlobalEvents.instance().fireApplicationClosingEvent(
                            GlobalEvents.Error_In_Game_Execution, true);
                }
            }
        };
    }

    @Override
    protected WorldUpdateEvent getUpdateEvent(final UpdateEvent src) {
        return new WorldUpdateEvent(this, (int) viewX, (int) viewY);
    }

    @Override
    public WorldUpdateEvent fireUpdateEvent(final UpdateEvent src) {
        Logger.instance().write("Game world is updating...", 10, false);
        final WorldUpdateEvent event = super.fireUpdateEvent(null);
        Logger.instance().write("Game world has updated", 10, false);
        event.readyForDraw.release(); //This tick is ready to be rendered.
        return event;
    }

    @Override
    public void applicationClosing(int reason) {
        stopTick();
        clearListeners();
        Logger.instance().write("Game world has been stopped successfully.",
                4, false);
    }

}
