package pe.upc.trackmyroute.ways.interfaces.rest.resources;

import java.util.List;

public record WayResource(Long id, String startLatitude, String startLongitude, String endLatitude, String endLongitude, List<WhereaboutResource> whereabout) {
}
