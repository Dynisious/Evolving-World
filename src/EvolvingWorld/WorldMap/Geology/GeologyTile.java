package EvolvingWorld.WorldMap.Geology;

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
    private int waterTable; //The amount of water to be found in this tile.
    public final int getWaterTable() {
        return waterTable;
    }
    public final void setWaterTable(final int val) {
        if (val < 0) {
            throw new ArithmeticException(
                    "ERROR : val must be greater than or equal to 0. val=" + val);
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
     * @param x          The x coordinate of this Tile.
     * @param y          The y coordinate of this Tile.
     * @param minerals   The quantity of different minerals in this
     *                   GeologyTile.
     * @param gemstones  The quantity of different gemstones in this
     *                   GeologyTile.
     * @param waterTable The amount of water to be found in the GeologyTile.
     * @param integrity  The integrity of this GeologyTile.
     */
    public GeologyTile(final int x, final int y, final int[] minerals,
                       final int[] gemstones, final int waterTable,
                       final double integrity) {
        super(x, y);
        this.minerals = minerals;
        this.gemstones = gemstones;
        setWaterTable(waterTable);
        setIntegrity(integrity);
    }
    @Override
    public void objectUpdated(GeologyUpdateEvent u) {

    }

}
