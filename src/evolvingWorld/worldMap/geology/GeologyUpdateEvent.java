package evolvingWorld.worldMap.geology;

import evolvingWorld.worldMap.WorldUpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class GeologyUpdateEvent extends WorldUpdateEvent {

    /**
     * <p>
     * Creates and returns a new GeologyUpdateEvent with the passed values.</p>
     *
     * @param event The WorldUpdateEvent which was just thrown.
     */
    public GeologyUpdateEvent(final WorldUpdateEvent event) {
        super(event.world, event.x, event.y);
    }

}
