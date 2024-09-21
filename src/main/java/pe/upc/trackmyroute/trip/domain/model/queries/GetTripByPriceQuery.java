package pe.upc.trackmyroute.trip.domain.model.queries;

import java.math.BigDecimal;

public record GetTripByPriceQuery(BigDecimal price) {
    public GetTripByPriceQuery {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }
}