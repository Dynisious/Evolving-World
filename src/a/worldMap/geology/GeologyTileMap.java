package evolvingWorld.worldMap.geology;

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
public class GeologyTileMap extends TileMap<GeologyTile, GeologyUpdateEvent, WorldUpdateEvent>
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
    public void objectUpdated(final WorldUpdateEvent u) {
        Logger.instance().write("Updating world crust...", 10, false);
        fireUpdateEvent(u);
    }

    @Override
    public void applicationClosing(int reason) {
        clearListeners();
    }

    @Override
    protected GeologyUpdateEvent getUpdateEvent(final WorldUpdateEvent src) {
        return new GeologyUpdateEvent(src);
    }

}
