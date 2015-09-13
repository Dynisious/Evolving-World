package EvolvingWorld.WorldMap;

import EvolvingWorld.AppUtils.Events.UpdateEvent;
import EvolvingWorld.AppUtils.Events.UpdateListener;
/**
 * <p>
 * A tagging interface to identify tiles which would be stored in a TileMap.</p>
 *
 * @author Dynisious 09/09/2015
 * @version 0.0.1
 * @param <T> The Type of UpdateEvent this Tile handles.
 */
public abstract class Tile<T extends UpdateEvent> implements UpdateListener<T> {
    public final int x; //The x coordinate of this Tile.
    public final int y; //The y coordinate of this Tile.

    /**
     * <p>
     * Creates and returns a Tile object with the passed x and y coordinates.
     * @param x
     * @param y 
     */
    public Tile(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

}
