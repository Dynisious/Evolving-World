package EvolvingWorld.WorldMap;

import AppUtils.Events.Updateable;
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
    private final Timer worldTick; //The Timer object for the game.

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
        worldTick = new Timer("EW: World Tick", true);
        worldTick.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fireUpdateEvent();
            }
        }, 0, tickPeriod);
    }

    @Override
    protected WorldUpdateEvent getUpdateEvent() {
        return new WorldUpdateEvent(this);
    }

}
