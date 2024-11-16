package pe.upc.trackmyroute.ways.interfaces.rest.resources;

public record CreateWayResource(String startLatitude, String startLongitude, String endLatitude, String endLongitude) {
}
