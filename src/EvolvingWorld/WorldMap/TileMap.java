package EvolvingWorld.WorldMap;

import EvolvingWorld.AppUtils.Events.UpdateEvent;
import EvolvingWorld.AppUtils.Events.Updateable;
/**
 * <p>
 * Base class for the different maps of Tile types.</p>
 *
 * @author Dynisious 09/09/2015
 * @param <T> The type of Tile that this TileMap will hold.
 * @param <E> The type of UpdateEvent that gets thrown by this TileMap.
 *
 * @versions 0.0.1
 */
public abstract class TileMap<T extends Tile, E extends UpdateEvent> extends Updateable<E> {
    private T[][] tiles;
    public T[][] getTiles() {
        return tiles;
    }
    public T getTile(final int x, final int y) {
        return tiles[x][y];
    }

    protected TileMap(final T[][] tiles) {
        this.tiles = tiles;
        for (final T[] ts : tiles) {
            for (final T t : ts) {
                addListener(t);
            }
        }
    }

}
