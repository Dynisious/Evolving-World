package EvolvingWorld.Terrain;

/**
 * <p>
 * This tile contains information about the terrain at a grid location in the
 * world.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public class TerrainTile {
    private int terrain = TerrainTileConsts.Dirt; //The type of terrain that this
    //TerrainTile is.
    private double fertility = -1; //How fertile the soils in this TerrainTile
    //are for growing plants.
    private double dampness = -1; //The dampness of the soils in this TerrainTile.
    private byte orientation = 0; //The orientation of this TerrainTile.

    /**
     * <p>
     * Creates and returns a new instance of a TerrainTile.</p>
     *
     * @param type The type of terrain in this TerrainTile.
     */
    public TerrainTile(final int type) {
        setTerrain(type);
    }

    /**
     * <p>
     * Creates and returns a new instance of a TerrainTile.</p>
     *
     * @param type        The type of terrain in this TerrainTile.
     * @param orientation The Orientation of this TerrainTile.
     */
    public TerrainTile(final int type, final byte orientation) {
        setTerrain(type);
        this.orientation = orientation;
    }

    /**
     * <p>
     * Changes the type of terrain in this TerrainTile and the tiles features
     * along with it.</p>
     *
     * @param type The type of terrain to change this to.
     */
    public final void setTerrain(final int type) {
        terrain = type;
        if (type == TerrainTileConsts.Bog) {
            //<editor-fold defaultstate="collapsed" desc="Bog Quality">
            fertility = 0;
            dampness = 0.98;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Clay) {
            //<editor-fold defaultstate="collapsed" desc="Clay Quality">
            fertility = 0.05;
            dampness = 0.02;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Dirt) {
            //<editor-fold defaultstate="collapsed" desc="Dirt Quality">
            fertility = 0.5;
            dampness = 0.05;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Farmland) {
            //<editor-fold defaultstate="collapsed" desc="Farmland Quality">
            fertility = 0.85;
            dampness = 0.15;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Forest) {
            //<editor-fold defaultstate="collapsed" desc="Forest Quality">
            fertility = 0.72;
            dampness = 0.18;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Grass) {
            //<editor-fold defaultstate="collapsed" desc="Grass Quality">
            fertility = 0.6;
            dampness = 0.1;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Mountain) {
            //<editor-fold defaultstate="collapsed" desc="Mountain Quality">
            fertility = 0.02;
            dampness = 0.05;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Mud) {
            //<editor-fold defaultstate="collapsed" desc="Mud Quality">
            fertility = 0.05;
            dampness = 0.5;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Ocean) {
            //<editor-fold defaultstate="collapsed" desc="Ocean Quality">
            fertility = 0.01;
            dampness = 1;
            //</editor-fold>
        } else if (type == TerrainTileConsts.River) {
            //<editor-fold defaultstate="collapsed" desc="River Quality">
            fertility = 0.02;
            dampness = 1;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Shore) {
            //<editor-fold defaultstate="collapsed" desc="Dirt Quality">
            fertility = 0.01;
            dampness = 1;
            //</editor-fold>
        } else if (type == TerrainTileConsts.Stone) {
            //<editor-fold defaultstate="collapsed" desc="Stone Quality">
            fertility = 0.01;
            dampness = 0.02;
            //</editor-fold>
        }
    }

}
