package EvolvingWorld.WorldMap.TopSoil;

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
public class TopSoilTileMap extends TileMap<TopSoilTile, TopSoilUpdateEvent>
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
