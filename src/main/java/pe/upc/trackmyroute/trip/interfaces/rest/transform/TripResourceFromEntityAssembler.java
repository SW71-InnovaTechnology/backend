package pe.upc.trackmyroute.trip.interfaces.rest.transform;

import pe.upc.trackmyroute.trip.domain.model.aggregates.Trip;
import pe.upc.trackmyroute.trip.interfaces.rest.resources.TripResource;

public class TripResourceFromEntityAssembler {
    public static TripResource transformResourceFromEntity(Trip entity) {
        return new TripResource(entity.getId(), entity.getOrigin(), entity.getDestination(), entity.getTime(), entity.getFare());
    }
}
