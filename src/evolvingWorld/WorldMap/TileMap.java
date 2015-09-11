package EvolvingWorld.WorldMap;

import AppUtils.Events.Updateable;
/**
 * <p>
 * Base class for the different maps of Tile types.</p>
 *
 * @author Dynisious 09/09/2015
 * @param <T> The type of Tile that this TileMap will hold.
 *
 * @versions 0.0.1
 */
public abstract class TileMap<T extends Tile> extends Updateable {
    private T[][] tiles;
    public T[][] getTiles() {
        return tiles;
    }

    protected TileMap(final T[][] tiles) {
        this.tiles = tiles;
    }

}
