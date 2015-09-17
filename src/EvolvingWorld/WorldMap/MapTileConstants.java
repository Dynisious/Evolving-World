package evolvingWorld.worldMap;

/**
 * <p>
 * The constants used across all map tiles.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public interface MapTileConstants {
    public int xWorldSize = 50; //The width of the world grid.
    public int yWorldSize = 50; //The height of the world grid.
    public int TileSideLength = 40; //The length of a Tile's side in pixels.
    //<editor-fold defaultstate="collapsed" desc="Orientations">
    public byte North = 0;
    public byte North_East = 1;
    public byte East = 2;
    public byte South_East = 3;
    public byte South = 4;
    public byte South_West = 5;
    public byte West = 6;
    public byte North_West = 7;
    //</editor-fold>
}
