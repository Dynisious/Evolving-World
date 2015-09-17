package evolvingWorld.worldMap.topSoil;

import evolvingWorld.worldMap.WorldUpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class TopSoilUpdateEvent extends WorldUpdateEvent {

    /**
     * <p>
     * Creates and returns a new TopSoilUpdateEvent with the passed values.</p>
     *
     * @param event The WorldUpdateEvent which just fired.
     */
    public TopSoilUpdateEvent(final WorldUpdateEvent event) {
        super(event.world, event.x, event.y);
    }

}
