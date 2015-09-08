package EvolvingWorld.Terrain;

import AppUtils.Logger;
import EvolvingWorld.MapTileConsts;
import java.util.Random;
/**
 * <p>
 * This stores the terrain for the game in a grid of tiles.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public class TerrainMap {
    private final TerrainTile[][] terrain = new TerrainTile[MapTileConsts.xWorldSize][MapTileConsts.yWorldSize];
    public void setTile(final int x, final int y, final int type) {
        try {
            terrain[x][y].setTerrain(type);
        } catch (IndexOutOfBoundsException ex) {
            String message = "ERROR : the coordinates for this tile(" + x + ", "
                    + y + ") are not on the map.\r\n" + ex.getMessage();
            for (StackTraceElement s : ex.getStackTrace()) {
                message += "\r\n" + s.toString();
            }
            Logger.instance().write(message, 10, false);
        }
    }

    /**
     * <p>
     * Creates and returns a new instance of TerrainMap with all tiles
     * initialised to dirt.</p>
     */
    public TerrainMap() {
        for (int x = 0; x < MapTileConsts.xWorldSize; x++) {
            for (int y = 0; y < MapTileConsts.yWorldSize; y++) {
                terrain[x][y] = new TerrainTile(TerrainTileConsts.Ocean);
            }
        }
    }

    /**
     * <p>
     * Generates terrain for the map.</p>
     *
     * @param seed The seed to use to create a pseudo random number generator
     *             for repeatable terrain generation.
     */
    public void generateTerrain(final long seed) {
        final Random rand = new Random(seed);

        //<editor-fold defaultstate="collapsed" desc="Forest Generation">
        for (int forest = 0; forest <= (int) Math.floor(
                4 * rand.nextDouble()); forest++) {
            final int x = (int) Math.floor(MapTileConsts.xWorldSize
                    * rand.nextDouble());
            final int y = (int) Math.floor(MapTileConsts.yWorldSize
                    * rand.nextDouble());
            final int radius = 2;
            //<editor-fold defaultstate="collapsed" desc="Set Tiles">
            for (int r = -radius; r <= radius; r++) { //Loop across
                //the entire diameter of the circle.
                final int yRange = (int) Math.sqrt(
                        Math.pow(radius, 2) - Math.pow(r, 2)); //The range
                //of y values that need to be filled.
                for (int o = -yRange; o <= yRange; o++) {
                    final int xPos = x + r; //The position of the x value.
                    if (xPos >= 0 || xPos < MapTileConsts.xWorldSize) {
                        final int yPos = y + o; //The position of the y value.
                        if (yPos >= 0 || yPos < MapTileConsts.yWorldSize) {
                            if (rand.nextDouble() < 0.75) { //Chance to fill the
                                //TerrainTile with Forest.
                                setTile(xPos, yPos, TerrainTileConsts.Forest);
                            } else {
                                setTile(xPos, yPos, TerrainTileConsts.Plains);
                            }
                        }
                    }
                }
            }
            //</editor-fold>
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Mountain Range Generation">
        for (int mountainRange = 0; mountainRange <= (int) Math.floor(
                2 * rand.nextDouble()); mountainRange++) {
            final int x = (int) Math.floor(MapTileConsts.xWorldSize
                    * rand.nextDouble());
            final int y = (int) Math.floor(MapTileConsts.yWorldSize
                    * rand.nextDouble());
            final int radius = 3;
            //<editor-fold defaultstate="collapsed" desc="Set Tiles">
            for (int r = -radius; r <= radius; r++) { //Loop across
                //the entire diameter of the circle.
                final int yRange = (int) Math.sqrt(
                        Math.pow(radius, 2) - Math.pow(r, 2)); //The range
                //of y values that need to be filled.
                for (int o = -yRange; o <= yRange; o++) {
                    final int xPos = x + r; //The position of the x value.
                    if (xPos >= 0 || xPos < MapTileConsts.xWorldSize) {
                        final int yPos = y + o; //The position of the y value.
                        if (yPos >= 0 || yPos < MapTileConsts.yWorldSize) {
                            if (rand.nextDouble() < 0.6) { //Chance to fill the
                                //TerrainTile with Plains.
                                if (terrain[x][y].getTerrain() != TerrainTileConsts.Forest) {
                                    setTile(xPos, yPos, TerrainTileConsts.Plains);
                                }
                            } else {
                                setTile(xPos, yPos, TerrainTileConsts.Mountain);
                            }
                        }
                    }
                }
            }
            //</editor-fold>
        }
        //</editor-fold>

        for (int x = 0; x < MapTileConsts.xWorldSize; x++) {
            System.out.print("|");
            for (int y = 0; y < MapTileConsts.yWorldSize; y++) {
                String s;
                if (terrain[x][y].getTerrain() == TerrainTileConsts.Ocean) {
                    s = "O|";
                } else if (terrain[x][y].getTerrain() == TerrainTileConsts.Plains) {
                    s = "G|";
                } else if (terrain[x][y].getTerrain() == TerrainTileConsts.Forest) {
                    s = "F|";
                } else {
                    s = "M|";
                }
                System.out.print(s);
            }
            System.out.print("\r\n");
        }
    }

}
