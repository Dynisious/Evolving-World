package EvolvingWorld.WorldMap;

import EvolvingWorld.AppUtils.Events.UpdateEvent;
import java.util.concurrent.Semaphore;
/**
 * <p>
 * Event is fired when the world map updates.</p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public class WorldUpdateEvent implements UpdateEvent {
    public final WorldMap world; //The WorldMap which just updated.
    public final int x; //The x coordinate of the top leftmost viewable position.
    public final int y; //The y coordinate of the top leftmost viewable position.
    public final Semaphore readyForDraw = new Semaphore(0); //A Semaphore to
    //alert the drawing Thread with this WorldUpdateEvent that it is ready to be draw.

    /**
     * <p>
     * Creates and returns a new WorldUpdateEvent with the passed values.</p>
     *
     * @param world The WorldMap which just updated.
     * @param x     The x coordinate of the top leftmost viewable position.
     * @param y     The y coordinate of the top leftmost viewable position.
     */
    public WorldUpdateEvent(final WorldMap world, final int x, final int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }

}
