package evolvingWorld.WorldMap.Atmosphere;

import EvolvingWorld.Events.Updateable;
import EvolvingWorld.WorldMap.Tile;
/**
 * <p>
 * Stores all data concerning the atmosphere in a Tile.</p>
 *
 * @author Dynisious 10/09/2015
 * @versions 0.0.1
 */
public class AtmosphereTile extends Updateable implements Tile {
    public double temperature; //The temperature of the air in this Tile.
    private double humidity; //The humidity of the air in this Tile.
    public final double getHumidity() {
        return humidity;
    }
    public final void setHumidity(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("%-10.2f", val));
        }
        humidity = val;
    }
    private double pressure; //The atmospheric pressure in this Tile.
    public final double getPressure() {
        return pressure;
    }
    public final void setPressure(final double val) throws ArithmeticException {
        if (val < 0) {
            throw new ArithmeticException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        pressure = val;
    }
    private double toxicity; //The toxicity of the atmosphere in this Tile.
    public final double getToxicity() {
        return toxicity;
    }
    public final void setToxicity(final double val) throws ArithmeticException {
        if (val < 0 || val > 1) {
            throw new ArithmeticException(
                    "ERROR : val must be between 0 and 1. val="
                    + String.format("%-10.2f", val));
        }
        toxicity = val;
    }
    private double wind; //The strength of the wind in this Tile.
    public final double getWind() {
        return wind;
    }
    public final void setWind(final double val) throws ArithmeticException {
        if (val < 0) {
            throw new ArithmeticException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        wind = val;
    }
    private double charge; //The electric charge in this AtmosphereTile.
    public final double getCharge() {
        return charge;
    }
    public final void setCharge(final double val) throws ArithmeticException {
        if (val < 0) {
            throw new ArithmeticException(
                    "ERROR : val must be greater than or equal to 0. val="
                    + String.format("%-10.2f", val));
        }
        charge = val;
    }
    private int weather; //The type of weather currently in this Tile.
    public final int getWeather() {
        return weather;
    }
    /**
     * <p>
     * Sets the type of weather in this Atmosphere tile and adjusts the tiles
     * values to match.
     *
     * @param weather The type of weather now in this AtmosphereTile.
     */
    public final void setWeather(final int weather) {
        this.weather = weather;
    }

    /**
     * <p>
     * Creates and returns a new instance of AtmosphereTile with the passed
     * values.</p>
     *
     * @param temperature The temperature in the Tile.
     * @param humidity    The humidity in the Tile.
     * @param pressure    The pressure in the Tile.
     * @param toxicity    The toxicity of the atmosphere in the Tile.
     * @param wind        The strength of the wind in the Tile.
     * @param weather     The type of weather in the Tile.
     * @param charge      The electric charge in the atmosphere in the Tile.
     */
    public AtmosphereTile(final double temperature, final double humidity,
                          final double pressure, final double toxicity,
                          final double wind, final int weather,
                          final double charge) {
        setWeather(weather);
        this.temperature = temperature;
        setHumidity(humidity);
        setPressure(pressure);
        setToxicity(toxicity);
        setWind(wind);
        setCharge(charge);
    }

    /**
     * <p>
     * Creates and returns a new instance of AtmosphereTile with the passed
     * weather.</p>
     *
     * @param weather The type of weather in the Tile.
     */
    public AtmosphereTile(final int weather) {
        setWeather(weather);
    }

}
