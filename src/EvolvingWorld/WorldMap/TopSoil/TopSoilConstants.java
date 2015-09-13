package EvolvingWorld.WorldMap.TopSoil;

/**
 * <p>
 * Constant values across all instances of TopSoilTiles.</p>
 *
 * @author Dynisious 08/09/2015
 * @version 0.0.1
 */
public interface TopSoilConstants {
    //<editor-fold defaultstate="collapsed" desc="TopSoil Types">
    /**
     * <p>
     * Flat grassy plains for kilometers. So-so fertility, not much
     * moisture.</p>
     */
    public int Plains = 0;
    public int RockyPlains = 1;
    public int Mountain = 2;
    public int Forest = 3;
    public int River = 4;
    public int Shore = 5;
    public int Ocean = 6;
    /**
     * <p>
     * Plain dirt. Decent fertility and water content.</p>
     */
    public int Dirt = 7;
    public int Clay = 8;
    public int Mud = 9;
    public int Bog = 10;
    //</editor-fold>
    public double GroundWaterAbsorbtionRate = 0.002;
}
