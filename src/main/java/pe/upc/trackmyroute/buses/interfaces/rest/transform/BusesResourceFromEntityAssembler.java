package pe.upc.trackmyroute.buses.interfaces.rest.transform;

import pe.upc.trackmyroute.buses.domain.model.aggregates.Bus;
import pe.upc.trackmyroute.buses.interfaces.rest.resources.BusesResource;

public class BusesResourceFromEntityAssembler {
    public static BusesResource toResourceFromEntity(Bus entity){
        return new BusesResource(
                entity.getId(),
                entity.getBusName(),
                entity.getBusStops()
        );
    }
}
