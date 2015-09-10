package EvolvingWorld.WorldMap.Geology;

import EvolvingWorld.Events.Updateable;
import EvolvingWorld.WorldMap.Tile;
/**
 * <p>
 * GeologyTiles hold all the values associated with geological activity or
 * stability inside a tile in the game world.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public class GeologyTile extends Updateable implements Tile {
    final public double[] minerals; //The different ores which can be found and
    //mined in this GeologyTile.
    final public double[] gemstones; //The different gemstones which can be found and
    //mined in this GeologyTile.
    private double waterTable; //The amount of water to be found in this tile.
    public final double getWaterTable() {
        return waterTable;
    }
    public final void setWaterTable(final double val) {
        if (val < 0) {
            throw new ArithmeticException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        waterTable = val;
    }
    private double integrity; //The integrity/toughness of the ground in this
    public final double getIntegrity() {
        return integrity;
    }
    public final void setIntegrity(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
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
     * @param minerals   The quantity of different minerals in this
     *                   GeologyTile.
     * @param gemstones  The quantity of different gemstones in this
     *                   GeologyTile.
     * @param waterTable The amount of water to be found in the GeologyTile.
     * @param integrity  The integrity of this GeologyTile.
     */
    public GeologyTile(final double[] minerals, final double[] gemstones,
                       final double waterTable, final double integrity) {
        this.minerals = minerals;
        this.gemstones = gemstones;
        setWaterTable(waterTable);
        setIntegrity(integrity);
    }

}
