package EvolvingWorld.WorldMap.TopSoil;

import EvolvingWorld.WorldMap.Tile;
/**
 * <p>
 * Holds all the values associated with the topsoil on a tile of the game
 * map.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.1.1
 */
public class TopSoilTile implements Tile<TopSoilUpdateEvent> {
    private double fertility; //The fertility of the soil in this tile.
    public final double getFertility() {
        return fertility;
    }
    public final void setFertitlity(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        fertility = val;
    }
    private double waterCont; //The water content of the soil in this tile.
    public final double getWaterCont() {
        return waterCont;
    }
    public final void setWaterCont(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        waterCont = val;
    }
    private double pollution; //How polluted the soils are in this tile.
    public final double getPollution() {
        return pollution;
    }
    public final void setPollution(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
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
    }

    /**
     * <p>
     * Creates and returns a new TopSoilTile of the passed type.</p>
     *
     * @param soilType The type of soil in this TopSoilTile.
     */
    public TopSoilTile(final int soilType) {
        setSoilType(soilType);
    }

    /**
     * <p>
     * Creates and returns a new TopSoilTile with the passed values.</p>
     *
     * @param fertility The fertility of the soil in this TopSoilTile.
     * @param soilType  The type of soil in this TopSoilTile.
     * @param waterCont The water content of the soil in this TopSoilTile.
     * @param pollution The pollution of the soil in this TopSoilTile.
     */
    public TopSoilTile(final double fertility, final double waterCont,
                       final double pollution, final int soilType) {
        setSoilType(soilType);
        setFertitlity(fertility);
        setWaterCont(waterCont);
        setPollution(pollution);
    }
    @Override
    public void objectUpdated(TopSoilUpdateEvent u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
