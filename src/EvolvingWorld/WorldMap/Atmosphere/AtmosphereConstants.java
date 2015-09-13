package EvolvingWorld.WorldMap.Atmosphere;

/**
 * <p>
 * </p>
 *
 * @author Dynisious 10/09/2015
 * @version 0.0.1
 */
public interface AtmosphereConstants {
    //<editor-fold defaultstate="collapsed" desc="Weather Types">
    /**
     * <p>
     * Fair weather, nice temperature, low humidity.</p>
     */
    public int Fair = 0;
    public int Overcast = 1;
    public int Clear = 2;
    /**
     * <p>
     * Rainy weather, dropping temperature, slowly dropping humidity.</p>
     */
    public int Rainy = 3;
    /**
     * <p>
     * Rapidly dropping temperatures, medium drop in humidity.</p>
     */
    public int Hail = 4;
    public int Snow = 5;
    public int Sunny = 6;
    public int Heat_Wave = 7;
    public int Muggy = 8;
    public int Stormy = 9;
    //</editor-fold>
    public double FairHumidityChangeCoefficient = 0.002;
    public double RainyHumidityChangeCoefficient = 0.002;
    public double HailHumidityChangeCoefficient = 0.004;
}
