package EvolvingWorld.WorldMap;

import AppUtils.Events.UpdateEvent;
import AppUtils.Events.UpdateListener;
/**
 * <p>
 * A tagging interface to identify tiles which would be stored in a TileMap.</p>
 *
 * @author Dynisious 09/09/2015
 * @version 0.0.1
 * @param <T> The Type of UpdateEvent this Tile handles.
 */
public interface Tile<T extends UpdateEvent> extends UpdateListener<T> {
}
