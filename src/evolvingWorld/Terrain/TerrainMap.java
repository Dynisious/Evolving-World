package EvolvingWorld.Terrain;

import AppUtils.Logger;
import EvolvingWorld.MapTileConsts;
/**
 * <p>
 * This stores the terrain for the game in a grid of tiles.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public class TerrainMap {
    private final TerrainTile[][] terrain = new TerrainTile[MapTileConsts.xWorldSize][MapTileConsts.yWorldSize];
    public void setTile(final int x, final int y, final int type) {
        try {
            terrain[x][y].setTerrain(type);
        } catch (IndexOutOfBoundsException ex) {
            String message = "ERROR : the coordinates for this tile(" + x + ", "
                    + y + ") are not on the map.\r\n" + ex.getMessage();
            for (StackTraceElement s : ex.getStackTrace()) {
                message += "\r\n" + s.toString();
            }
            Logger.instance().write(message, 10, false);
        }
    }

}
