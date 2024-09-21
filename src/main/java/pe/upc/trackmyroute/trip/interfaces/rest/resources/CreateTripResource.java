package pe.upc.trackmyroute.trip.interfaces.rest.resources;

public record CreateTripResource(String origin,
                                 String destination,
                                 String time,
                                 String fare) {
}