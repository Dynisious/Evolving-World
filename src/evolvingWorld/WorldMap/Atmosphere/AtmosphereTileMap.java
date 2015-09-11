package EvolvingWorld.WorldMap.Atmosphere;

import EvolvingWorld.WorldMap.*;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class AtmosphereTileMap extends TileMap<AtmosphereTile> {

    /**
     * <p>
     * Creates and returns a new AtmosphereTileMap with the passed map of
     * Atmosphere tiles.</p>
     *
     * @param atmosphere The grid of Atmosphere tiles that make up this map.
     */
    public AtmosphereTileMap(final AtmosphereTile[][] atmosphere) {
        super(atmosphere);
    }

}
