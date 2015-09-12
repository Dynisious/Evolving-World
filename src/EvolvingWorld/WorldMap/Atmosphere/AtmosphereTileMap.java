package EvolvingWorld.WorldMap.Atmosphere;

import EvolvingWorld.AppUtils.Events.UpdateListener;
import EvolvingWorld.WorldMap.TileMap;
import EvolvingWorld.WorldMap.WorldUpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class AtmosphereTileMap extends TileMap<AtmosphereTile, AtmosphereUpdateEvent>
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
    protected AtmosphereUpdateEvent getUpdateEvent() {
        return new AtmosphereUpdateEvent(this);
    }

    @Override
    public void objectUpdated(final WorldUpdateEvent u) {
        fireUpdateEvent();
    }

}
