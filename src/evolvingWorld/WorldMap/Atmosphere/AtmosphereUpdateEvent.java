package EvolvingWorld.WorldMap.Atmosphere;

import AppUtils.Events.UpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class AtmosphereUpdateEvent implements UpdateEvent {
    public final AtmosphereTileMap atmosphere; //The AtmosphereTileMap which just updated.

    /**
     * <p>
     * Creates and returns a new AtmosphereUpdateEvent with the passed
     * values.</p>
     *
     * @param atmosphere The AtmosphereTileMap which just updated.
     */
    public AtmosphereUpdateEvent(final AtmosphereTileMap atmosphere) {
        this.atmosphere = atmosphere;
    }

}
