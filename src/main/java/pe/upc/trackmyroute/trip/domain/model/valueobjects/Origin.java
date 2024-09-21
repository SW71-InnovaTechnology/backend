package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Origin(@NotBlank String value) {
    public Origin() {
        this(null);
    }

    public Origin {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Origin value cannot be null or blank");
        }
    }
}
