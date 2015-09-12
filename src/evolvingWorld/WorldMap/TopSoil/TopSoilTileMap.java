package EvolvingWorld.WorldMap.TopSoil;

import EvolvingWorld.WorldMap.TopSoil.*;
import AppUtils.Events.UpdateListener;
import EvolvingWorld.WorldMap.*;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class TopSoilTileMap extends TileMap<TopSoilTile>
        implements UpdateListener<WorldUpdateEvent> {

    /**
     * <p>
     * Creates and returns a new TopSoilTileMap with the passed map of
     * Atmosphere tiles.</p>
     *
     * @param crust The grid of Atmosphere tiles that make up this map.
     */
    public TopSoilTileMap(final TopSoilTile[][] crust) {
        super(crust);
    }

    @Override
    protected TopSoilUpdateEvent getUpdateEvent() {
        return new TopSoilUpdateEvent(this);
    }

    @Override
    public void objectUpdated(final WorldUpdateEvent u) {
        fireUpdateEvent();
    }

}
