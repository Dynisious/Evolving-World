package EvolvingWorld.WorldMap.Geology;

import EvolvingWorld.Events.Updateable;
/**
 * <p>
 * GeologyTiles hold all the values associated with geological activity or
 * stability inside a tile in the game world.</p>
 *
 * @author Dynisious 09/09/2015
 * @versions 0.0.1
 */
public class GeologyTile extends Updateable {
    public double[] minerals; //The different ores which can be found and
    //mined in this GeologyTile.
    public double[] gemstones; //The different gemstones which can be found and
    //mined in this GeologyTile.
    public double waterTable; //The amount of water to be found in this tile.
    private double integrity; //The integrity/toughness of the ground in this
    public final double getIntegrity() {
        return integrity;
    }
    public final void setIntegrity(final double val) {
        if (val < 0 || val > 100) {
            throw new ArithmeticException(
                    "Integrity can only be between 0 and 100. value="
                    + String.format("%3.4f", val));
        }
        integrity = val;
    }
    //tile. A low integrity is easier to mine but less structurally stable.

    public GeologyTile(final double[] minerals, final double[] gemstones,
                       final double waterTable, final double integrity) {
        this.minerals = minerals;
        this.gemstones = gemstones;
        this.waterTable = waterTable;
        setIntegrity(integrity);
    }

}
