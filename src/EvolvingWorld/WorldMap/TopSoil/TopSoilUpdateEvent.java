package EvolvingWorld.WorldMap.TopSoil;

import EvolvingWorld.AppUtils.Events.UpdateEvent;
/**
 * <p>
 * </p>
 *
 * @author Dynisious 11/09/2015
 * @versions 0.0.1
 */
public final class TopSoilUpdateEvent implements UpdateEvent {
    public final TopSoilTileMap crust; //The TopSoilTileMap which just updated.

    /**
     * <p>
     * Creates and returns a new AtmosphereUpdateEvent with the passed
     * values.</p>
     *
     * @param crust The TopSoilTileMap which just updated.
     */
    public TopSoilUpdateEvent(final TopSoilTileMap crust) {
        this.crust = crust;
    }

}
