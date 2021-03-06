package evolvingWorld.worldMap.atmosphere;

import evolvingWorld.worldMap.Tile;
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

    /**
     * <p>
     * Creates and returns a new instance of AtmosphereTile with the passed
     * values.</p>
     *
     * @param x           The x coordinate of this Tile.
     * @param y           The y coordinate of this Tile.
     * @param temperature The temperature in the Tile.
     * @param humidity    The humidity in the Tile.
     * @param pressure    The pressure in the Tile.
     * @param toxicity    The toxicity of the atmosphere in the Tile.
     * @param wind        The strength of the wind in the Tile.
     * @param weather     The type of weather in the Tile.
     * @param charge      The electric charge in the atmosphere in the
     *                    Tile.
     */
    public AtmosphereTile(final int x, final int y, final double temperature,
                          final double humidity,
                          final double pressure, final double toxicity,
                          final double wind, final double charge,
                          final int weather) {
        super(x, y);
        this.weather = weather;
        this.temperature = temperature;
        setHumidity(humidity);
        setPressure(pressure);
        setToxicity(toxicity);
        setWind(wind);
        setCharge(charge);
    }

    @Override
    public void objectUpdated(AtmosphereUpdateEvent u) {

    }

}
