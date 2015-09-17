package evolvingWorld.worldMap.atmosphere;

import evolvingWorld.worldMap.WorldUpdateEvent;
/**
 * <p>
 * The Event fired when the AtmosphereUpdates.</p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class AtmosphereUpdateEvent extends WorldUpdateEvent {

    /**
     * <p>
     * Creates and returns a new AtmosphereUpdateEvent with the passed
     * values.</p>
     *
     * @param event The WorldUpdateEvent which just fired.
     */
    public AtmosphereUpdateEvent(final WorldUpdateEvent event) {
        super(event.world, event.x, event.y);
    }

}
