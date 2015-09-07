package evolvingWorld;

/**
 * <p>
 * This stores the terrain for the game in a grid of tiles.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public class TerrainMap {
    private static final int xWorldSize = 10; //The width of the world grid.
    private static final int yWorldSize = 10; //The height of the world grid.
    private final TerrainTile[][] terrain = new TerrainTile[xWorldSize][yWorldSize];
}
