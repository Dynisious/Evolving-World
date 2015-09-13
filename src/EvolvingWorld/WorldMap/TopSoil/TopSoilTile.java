package EvolvingWorld.WorldMap.TopSoil;

import EvolvingWorld.WorldMap.Geology.GeologyTile;
import EvolvingWorld.WorldMap.Tile;
/**
 * <p>
 * Holds all the values associated with the topsoil on a tile of the game
 * map.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.1.1
 */
public class TopSoilTile extends Tile<TopSoilUpdateEvent> {
    private double fertility; //The fertility of the soil in this tile.
    public final double getFertility() {
        return fertility;
    }
    public final void setFertitlity(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        fertility = val;
    }
    private double waterContent; //The water content of the soil in this tile.
    public final double getWaterContent() {
        return waterContent;
    }
    public final void setWaterContent(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        waterContent = val;
    }
    private double pollution; //How polluted the soils are in this tile.
    public final double getPollution() {
        return pollution;
    }
    public final void setPollution(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        pollution = val;
    }
    private int soilType; //The type of TopSoil contained in this tile.
    public final int getSoilType() {
        return soilType;
    }
    /**
     * <p>
     * Sets the type of soil in the TopSoilTile and changes it's values to match
     * the new type.</p>
     *
     * @param soilType The type of soil to be kept in this TopSoilTile.
     */
    public final void setSoilType(final int soilType) {
        this.soilType = soilType;
        if (soilType == TopSoilConstants.Dirt) {
            fertility = 0.8;
            waterContent = 0.3;
            pollution = 0.02;
        }
    }

    /**
     * <p>
     * Creates and returns a new TopSoilTile of the passed type.</p>
     *
     * @param x
     * @param y
     * @param soilType The type of soil in this TopSoilTile.
     */
    public TopSoilTile(final int x, final int y, final int soilType) {
        super(x, y);
        setSoilType(soilType);
    }

    /**
     * <p>
     * Creates and returns a new TopSoilTile with the passed values.</p>
     *
     * @param x
     * @param y
     * @param fertility The fertility of the soil in this TopSoilTile.
     * @param soilType  The type of soil in this TopSoilTile.
     * @param waterCont The water content of the soil in this TopSoilTile.
     * @param pollution The pollution of the soil in this TopSoilTile.
     */
    public TopSoilTile(final int x, final int y, final double fertility,
                       final double waterCont, final double pollution,
                       final int soilType) {
        super(x, y);
        setSoilType(soilType);
        setFertitlity(fertility);
        setWaterContent(waterCont);
        setPollution(pollution);
    }

    @Override
    public void objectUpdated(TopSoilUpdateEvent u) {
        final GeologyTile crust = u.world.crust.getTile(x, y); //The crust layer
        final double groundWater; //The amount of water that is drawn up to the soil.
        if (crust.getWaterTable() < 0.25) { //Drain water.
            groundWater = -Double.min(waterContent, Double.min(
                    1 - crust.getWaterTable(),
                    TopSoilConstants.GroundWaterAbsorbtionRate));
        } else { //Pull water.
            groundWater = Double.min(1 - waterContent, Double.min(
                    crust.getWaterTable(),
                    TopSoilConstants.GroundWaterAbsorbtionRate));
        }
        setWaterContent(waterContent + groundWater);
        crust.setWaterTable(crust.getWaterTable() - groundWater);
    }

}
