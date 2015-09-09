package EvolvingWorld.TopSoil;

import EvolvingWorld.Events.Updateable;
/**
 * <p>
 * Holds all the values associated with the topsoil on a tile of the game
 * map.</p>
 *
 * @author Dynisious 08/09/2015
 * @versions 0.0.1
 */
public class TopSoilTile extends Updateable {
    private double fertility; //The fertility of the soil in this tile.
    public double getFertility() {
        return fertility;
    }
    public void setFertitlity(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        fertility = val;
    }
    private double waterCont; //The water content of the soil in this tile.
    public double getWaterCont() {
        return waterCont;
    }
    public void setWaterCont(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        waterCont = val;
    }
    private double pollution; //How polluted the soils are in this tile.
    public double getPollution() {
        return pollution;
    }
    public void setPollution(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : The parameter val has limits 0 <= val <= 1, val=" + val);
        }
        pollution = val;
    }
    private int soilType; //The type of TopSoil contained in this tile.
    /**
     * <p>
     * Sets the type of soil in the TopSoilTile and changes it's values to match
     * the new type.</p>
     *
     * @param soilType The type of soil to be kept in this TopSoilTile.
     */
    public void setSoilType(final int soilType) {
        this.soilType = soilType;
    }

    public TopSoilTile(final int soilType) {
        setSoilType(soilType);
    }

}
