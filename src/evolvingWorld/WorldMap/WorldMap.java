package EvolvingWorld.WorldMap;

import AppUtils.Events.Updateable;
import AppUtils.GlobalEvents;
import AppUtils.Logger;
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
public final class WorldMap extends Updateable<WorldUpdateEvent> {
    public final AtmosphereTileMap atmosphere; //The atmosphere of the world.
    public final GeologyTileMap crust; //The world's crust.
    public final TopSoilTileMap topSoil; //The world's top soil.
    private Timer worldTick; //The Timer object for the game.
    private final long tickPeriod;
    public void startTick() {
        if (worldTick == null) {
            worldTick = new Timer("EW: World Tick", true);
            worldTick.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        fireUpdateEvent();
                    } catch (Exception ex) {
                        String message = "ERROR : There was an error during game execution. "
                                + ex.getClass().getName() + ": " + ex.getMessage();
                        for (final StackTraceElement s : ex.getStackTrace()) {
                            message += "\r\n    Line:" + s.getLineNumber() + "\t" + s.toString();
                        }
                        Logger.instance().write(message, 1, true);
                        GlobalEvents.instance().applicationClosing(
                                GlobalEvents.Error_In_Execution, true);
                    }
                }
            }, 0, tickPeriod);
        }
    }
    public void stopTick() {
        if (worldTick != null) {
            synchronized (worldTick) {
                worldTick.cancel();
                worldTick = new Timer("EW: World Tick", true);
                worldTick.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            fireUpdateEvent();
                        } catch (Exception ex) {
                            String message = "ERROR : There was an error during game execution. "
                                    + ex.getClass().getName() + ": " + ex.getMessage();
                            for (final StackTraceElement s : ex.getStackTrace()) {
                                message += "\r\n    Line:" + s.getLineNumber() + "\t" + s.toString();
                            }
                            Logger.instance().write(message, 1, true);
                            GlobalEvents.instance().applicationClosing(
                                    GlobalEvents.Error_In_Execution, true);
                        }
                    }
                }, 0, tickPeriod);
            }
        }
    }

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
    }

    @Override
    protected WorldUpdateEvent getUpdateEvent() {
        return new WorldUpdateEvent(this);
    }

}
