package evolvingWorld.worldMap;

import evolvingWorld.appUtils.events.UpdateEvent;
import evolvingWorld.appUtils.events.Updateable;
/**
 * <p>
 * Base class for the different maps of Tile types.</p>
 *
 * @author Dynisious 09/09/2015
 * @param <T> The type of Tile that this TileMap will hold.
 * @param <E> The type of UpdateEvent that gets thrown by this TileMap.
 * @param <X> The type of UpdateEvent that can cause this UpdateAble to update.
 *
 * @versions 0.0.1
 */
public abstract class TileMap<T extends Tile, E extends UpdateEvent, X extends UpdateEvent>
        extends Updateable<E, X> {
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
