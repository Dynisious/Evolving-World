package EvolvingWorld.WorldMap.Geology;

import AppUtils.Events.UpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class GeologyUpdateEvent implements UpdateEvent {
    public final GeologyTileMap crust; //The TopSoilTileMap which just updated.

    /**
     * <p>
     * Creates and returns a new AtmosphereUpdateEvent with the passed
     * values.</p>
     *
     * @param crust The TopSoilTileMap which just updated.
     */
    public GeologyUpdateEvent(final GeologyTileMap crust) {
        this.crust = crust;
    }

}
