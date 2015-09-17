package EvolvingWorld.WorldMap.Atmosphere;

import EvolvingWorld.AppUtils.Events.UpdateListener;
import EvolvingWorld.AppUtils.Logger;
import EvolvingWorld.WorldMap.TileMap;
import EvolvingWorld.WorldMap.WorldUpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class AtmosphereTileMap extends TileMap<AtmosphereTile, AtmosphereUpdateEvent, WorldUpdateEvent>
        implements UpdateListener<WorldUpdateEvent> {

    /**
     * <p>
     * Creates and returns a new AtmosphereTileMap with the passed map of
     * Atmosphere tiles.</p>
     *
     * @param atmosphere The grid of Atmosphere tiles that make up this map.
     */
    public AtmosphereTileMap(final AtmosphereTile[][] atmosphere) {
        super(atmosphere);
    }

    @Override
    public void objectUpdated(final WorldUpdateEvent u) {
        Logger.instance().write("Updating world atmosphere...", 10, false);
        fireUpdateEvent(u);
    }

    @Override
    public void applicationClosing(int reason) {
        clearListeners();
    }

    @Override
    protected AtmosphereUpdateEvent getUpdateEvent(final WorldUpdateEvent src) {
        return new AtmosphereUpdateEvent(src);
    }

}
