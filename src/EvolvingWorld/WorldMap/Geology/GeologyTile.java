package EvolvingWorld.WorldMap.Geology;

import EvolvingWorld.WorldMap.MapTileConstants;
import EvolvingWorld.WorldMap.Tile;
/**
 * <p>
 * GeologyTiles hold all the values associated with geological activity or
 * stability inside a tile in the game world.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public class GeologyTile extends Tile<GeologyUpdateEvent> {
    final public int[] minerals; //The different ores which can be found and
    //mined in this GeologyTile.
    final public int[] gemstones; //The different gemstones which can be found and
    //mined in this GeologyTile.
    private double waterTable; //The amount of water to be found in this tile.
    public final double getWaterTable() {
        return waterTable;
    }
    public final void setWaterTable(final double val) {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("-10.2", val));
        }
        waterTable = val;
    }
    private double integrity; //The integrity/toughness of the ground in this
    public final double getIntegrity() {
        return integrity;
    }
    public final void setIntegrity(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "Integrity can only be between 0 and 1. value="
                    + String.format("%-10.2f", val));
        }
        integrity = val;
    }
    //tile. A low integrity is easier to mine but less structurally stable.

    /**
     * <p>
     * Creates and returns a new GeologyTile with the passed values.</p>
     *
     * @param x          The x coordinate of this Tile.
     * @param y          The y coordinate of this Tile.
     * @param minerals   The quantity of different minerals in this
     *                   GeologyTile.
     * @param gemstones  The quantity of different gemstones in this
     *                   GeologyTile.
     * @param waterTable The amount of water to be found in the GeologyTile.
     */
    public GeologyTile(final int x, final int y, final int[] minerals,
                       final int[] gemstones, final double waterTable) {
        super(x, y);
        this.minerals = minerals;
        this.gemstones = gemstones;
        setWaterTable(waterTable);
        setIntegrity(integrity);
    }
    
    @Override
    public void objectUpdated(GeologyUpdateEvent u) {
        final GeologyTile[][] crust = u.world.crust.getTiles(); //The crust layer.
        for (int i = -1; i < 2; i++) {
            if (x + i > 0 && x + i < MapTileConstants.xWorldSize) {
                for (int j = -1; j < 2; j++) {
                    if (i != 0 && j != 0 && y + j > 0
                            && y + j < MapTileConstants.yWorldSize) { //It's not
                        //this tile.
                        final GeologyTile t = crust[x + i][y + j]; //The tile
                        //being flowed to.
                        final double flow; //The water moving between this tile
                        //and the adjacent tile.
                        if (t.getWaterTable() < waterTable) { //Flow to this tile.
                            flow = Double.max(t.getWaterTable() - waterTable,
                                    Double.max(-t.getWaterTable() / waterTable,
                                            waterTable - 1));
                        } else { //Flow from this tile.
                            flow = Double.min(t.getWaterTable() - waterTable,
                                    Double.min(t.getWaterTable() / waterTable,
                                            1 - waterTable));
                        }
                        setWaterTable(waterTable + flow);
                        t.setWaterTable(t.getWaterTable() - flow);
                    }
                }
            }
        }
        setIntegrity(1 - waterTable);
    }

}
