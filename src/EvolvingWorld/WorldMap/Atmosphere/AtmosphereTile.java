package EvolvingWorld.WorldMap.Atmosphere;

import EvolvingWorld.WorldMap.Geology.GeologyTile;
import EvolvingWorld.WorldMap.Tile;
import EvolvingWorld.WorldMap.TopSoil.TopSoilTile;
/**
 * <p>
 * Stores all data concerning the atmosphere in a Tile.</p>
 *
 * @author Dynisious 10/09/2015
 * @versions 0.0.1
 */
public class AtmosphereTile extends Tile<AtmosphereUpdateEvent> {
    public double temperature; //The temperature of the air in this Tile.
    private double humidity; //The humidity of the air in this Tile.
    public final double getHumidity() {
        return humidity;
    }
    public final void setHumidity(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("%-10.2f", val));
        }
        humidity = val;
    }
    private double pressure; //The atmospheric pressure in this Tile.
    public final double getPressure() {
        return pressure;
    }
    public final void setPressure(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("%-10.2f", val));
        }
        pressure = val;
    }
    private double toxicity; //The toxicity of the atmosphere in this Tile.
    public final double getToxicity() {
        return toxicity;
    }
    public final void setToxicity(final double val) throws
            IllegalArgumentException {
        if (val < 0 || val > 1) {
            throw new IllegalArgumentException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("%-10.2f", val));
        }
        toxicity = val;
    }
    private double wind; //The strength of the wind in this Tile.
    public final double getWind() {
        return wind;
    }
    public final void setWind(final double val) throws IllegalArgumentException {
        if (val < 0) {
            throw new IllegalArgumentException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        wind = val;
    }
    private double charge; //The electric charge in this AtmosphereTile.
    public final double getCharge() {
        return charge;
    }
    public final void setCharge(final double val) throws
            IllegalArgumentException {
        if (val < 0) {
            throw new IllegalArgumentException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        charge = val;
    }
    public int weather; //The type of weather currently in this Tile.
    private double heatChangeCoefficient; //The amount of heat that gets gained
    //relative to the humidity.

    /**
     * <p>
     * Creates and returns a new instance of AtmosphereTile with the passed
     * values.</p>
     *
     * @param x                   The x coordinate of this Tile.
     * @param y                   The y coordinate of this Tile.
     * @param temperature         The temperature in the Tile.
     * @param humidity            The humidity in the Tile.
     * @param pressure            The pressure in the Tile.
     * @param toxicity            The toxicity of the atmosphere in the Tile.
     * @param wind                The strength of the wind in the Tile.
     * @param weather             The type of weather in the Tile.
     * @param charge              The electric charge in the atmosphere in the
     *                            Tile.
     * @param heatGainCoefficient The amount of heat that gets gained each
     *                            update relative to humidity.
     */
    public AtmosphereTile(final int x, final int y, final double temperature,
                          final double humidity,
                          final double pressure, final double toxicity,
                          final double wind, final double charge,
                          final int weather, final double heatGainCoefficient) {
        super(x, y);
        this.weather = weather;
        this.temperature = temperature;
        this.heatChangeCoefficient = heatGainCoefficient;
        setHumidity(humidity);
        setPressure(pressure);
        setToxicity(toxicity);
        setWind(wind);
        setCharge(charge);
    }

    @Override
    public void objectUpdated(AtmosphereUpdateEvent u) {
        final TopSoilTile soil = u.world.topSoil.getTile(x, y); //The topsoil
        //in this tile.
        double evaporation = Double.max(0, Double.min(1 - humidity, Double.min(
                soil.getWaterContent(), soil.getWaterContent()
                * temperature / pressure / 100))); //The amount of water that has
        //evaporated from the soil into the air.
        setHumidity(humidity + evaporation);
        soil.setWaterContent(soil.getWaterContent() - evaporation);
        temperature += heatChangeCoefficient * humidity;

        //<editor-fold defaultstate="collapsed" desc="Weather Effects">
        if (weather == AtmosphereConstants.Fair) {
            heatChangeCoefficient = Double.min(1, heatChangeCoefficient + 0.002);
        } else if (weather == AtmosphereConstants.Rainy) {
            double rain = Double.min(1 - humidity,
                    AtmosphereConstants.RainyHumidityChangeCoefficient * humidity);
            setHumidity(humidity - rain);
            if (soil.getWaterContent() + rain > 1) { //That much water can't be
                //in the soil.
                rain -= soil.getWaterContent();
                soil.setWaterContent(1);
                final GeologyTile crust = u.world.crust.getTile(x, y);
                crust.setWaterTable(crust.getWaterTable() + rain);
            }
            heatChangeCoefficient = Double.max(-1, heatChangeCoefficient - 0.07);
        } else if (weather == AtmosphereConstants.Hail) {
            double hail = Double.min(1 - humidity,
                    AtmosphereConstants.HailHumidityChangeCoefficient * humidity);
            setHumidity(humidity - hail);
            if (hail + soil.getWaterContent() > 1) { //That much water can't be
                //in the soil.
                hail -= soil.getWaterContent();
                soil.setWaterContent(1);
                final GeologyTile crust = u.world.crust.getTile(x, y);
                crust.setWaterTable(crust.getWaterTable() + hail);
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Select Weather.">
        if (100 * humidity * pressure / temperature > 1) { //Water will condence.
            if (temperature > 5) { //Above freezing temperatures.
                weather = AtmosphereConstants.Rainy;
            } else if (Math.abs(temperature) < 5) { //At freezing temperatures.
                weather = AtmosphereConstants.Hail;
            }
        } else { //Water wont condense.
            weather = AtmosphereConstants.Fair;
        }
        //</editor-fold>
    }

}
