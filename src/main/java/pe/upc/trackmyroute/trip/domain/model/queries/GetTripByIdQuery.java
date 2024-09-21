package pe.upc.trackmyroute.trip.domain.model.queries;

public record GetTripByIdQuery(Long id){
    public GetTripByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
