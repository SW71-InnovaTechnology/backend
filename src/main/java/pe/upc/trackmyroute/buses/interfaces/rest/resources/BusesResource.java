package pe.upc.trackmyroute.buses.interfaces.rest.resources;

import pe.upc.trackmyroute.buses.domain.model.entities.BusStop;

import java.util.List;

public record BusesResource(
        Long id,
        String busName,
        List<BusStop> stops
) {
}
