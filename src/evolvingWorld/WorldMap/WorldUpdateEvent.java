package EvolvingWorld.WorldMap;

import AppUtils.Events.UpdateEvent;
/**
 * <p>
 * Event is fired when the world map updates.</p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class WorldUpdateEvent implements UpdateEvent {
    public final WorldMap world; //The WorldMap which just updated.

    /**
     * <p>
     * Creates and returns a new WorldUpdateEvent with the passed values.</p>
     *
     * @param world The WorldMap which just updated.
     */
    public WorldUpdateEvent(final WorldMap world) {
        this.world = world;
    }

}
