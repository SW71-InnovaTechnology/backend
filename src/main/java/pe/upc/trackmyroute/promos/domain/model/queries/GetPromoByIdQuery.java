package pe.upc.trackmyroute.promos.domain.model.queries;

public record GetPromoByIdQuery(Long id) {
public GetPromoByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
