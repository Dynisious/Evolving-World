package EvolvingWorld.WorldMap.Geology;

import AppUtils.Events.UpdateListener;
import EvolvingWorld.WorldMap.*;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class GeologyTileMap extends TileMap<GeologyTile>
        implements UpdateListener<WorldUpdateEvent> {

    /**
     * <p>
     * Creates and returns a new GeologyTileMap with the passed map of
     * Atmosphere tiles.</p>
     *
     * @param crust The grid of Atmosphere tiles that make up this map.
     */
    public GeologyTileMap(final GeologyTile[][] crust) {
        super(crust);
    }

    @Override
    protected GeologyUpdateEvent getUpdateEvent() {
        return new GeologyUpdateEvent(this);
    }

    @Override
    public void objectUpdated(final WorldUpdateEvent u) {
        fireUpdateEvent();
    }

}
