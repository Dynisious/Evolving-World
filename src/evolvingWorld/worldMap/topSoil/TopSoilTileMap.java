package evolvingWorld.worldMap.topSoil;

import evolvingWorld.appUtils.events.UpdateListener;
import evolvingWorld.appUtils.Logger;
import evolvingWorld.worldMap.TileMap;
import evolvingWorld.worldMap.WorldUpdateEvent;
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
    protected TopSoilUpdateEvent getUpdateEvent(final WorldUpdateEvent src) {
        return new TopSoilUpdateEvent(src);
    }

}
