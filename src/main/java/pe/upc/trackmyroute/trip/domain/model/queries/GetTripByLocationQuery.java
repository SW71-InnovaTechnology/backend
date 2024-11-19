package pe.upc.trackmyroute.trip.domain.model.queries;

public record GetTripByLocationQuery(String latitude, String longitude) {
    public GetTripByLocationQuery {
        if (latitude == null || latitude.isBlank()) {
            throw new IllegalArgumentException("Latitude cannot be null or blank");
        }
        if (longitude == null || longitude.isBlank()) {
            throw new IllegalArgumentException("Longitude cannot be null or blank");
        }
    }
}