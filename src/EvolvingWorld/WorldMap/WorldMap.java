package EvolvingWorld.WorldMap;

import EvolvingWorld.AppUtils.Events.GlobalEventListener;
import EvolvingWorld.AppUtils.Events.Updateable;
import EvolvingWorld.AppUtils.GlobalEvents;
import EvolvingWorld.AppUtils.Logger;
import EvolvingWorld.WorldMap.Atmosphere.AtmosphereTileMap;
import EvolvingWorld.WorldMap.Geology.GeologyTileMap;
import EvolvingWorld.WorldMap.TopSoil.TopSoilTileMap;
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
public final class WorldMap extends Updateable<WorldUpdateEvent>
        implements GlobalEventListener {
    public final AtmosphereTileMap atmosphere; //The atmosphere of the world.
    public final GeologyTileMap crust; //The world's crust.
    public final TopSoilTileMap topSoil; //The world's top soil.
    private Timer worldTick; //The Timer object for the game.
    private final long tickPeriod; //The number of milliseconds between each update tick.
    private final TimerTask task; //The TimerTask executed each update tick.
    public void startTick() {
        if (worldTick == null) {
            worldTick = new Timer("EW: World Tick", true);
            worldTick.scheduleAtFixedRate(task, 0, tickPeriod);
        }
    }
    public void stopTick() {
        if (worldTick != null) {
            synchronized (worldTick) {
                worldTick.cancel();
                worldTick = null;
            }
        }
    }
    private int viewX; //The x coordinate of the top leftmost Tile in view.
    private int viewY; //The y coordinate of the top leftmost Tile in view.

    /**
     * <p>
     * Creates and returns a new WorldMap with the passed values.</p>
     *
     * @param atmosphere The atmosphere of the world.
     * @param crust      The crust of the world.
     * @param topSoil    The top soil of the world.
     * @param tickPeriod The number of milliseconds between each game tick.
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
                    fireUpdateEvent();
                } catch (Exception ex) {
                    Logger.instance().logWithStackTrace(
                            "ERROR : There was an error during game execution. "
                            + ex.getClass().getName() + ": " + ex.getMessage(),
                            1, true, ex.getStackTrace());
                    GlobalEvents.instance().applicationClosing(
                            GlobalEvents.Error_In_Execution, true);
                }
            }
        };
    }

    @Override
    protected WorldUpdateEvent getUpdateEvent() {
        return new WorldUpdateEvent(this, viewX, viewY);
    }

    @Override
    public void applicationClosing(int reason) {
        stopTick();
        clearListeners();
        Logger.instance().write("Game Tick has been stopped successfully.",
                4, false);
    }

}
