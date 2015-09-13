package EvolvingWorld.WorldMap.TopSoil;

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
public class TopSoilTileMap extends TileMap<TopSoilTile, TopSoilUpdateEvent, WorldUpdateEvent>
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
    public void objectUpdated(final WorldUpdateEvent u) {
        Logger.instance().write("Updating world top soil...", 10, false);
        fireUpdateEvent(u);
    }

    @Override
    public void applicationClosing(int reason) {
        clearListeners();
    }

    @Override
    protected TopSoilUpdateEvent getUpdateEvent()
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "This is not a supported operation for "
                + getClass().getSimpleName());
    }

    @Override
    protected TopSoilUpdateEvent getUpdateEvent(final WorldUpdateEvent src) {
        return new TopSoilUpdateEvent(src);
    }

}
