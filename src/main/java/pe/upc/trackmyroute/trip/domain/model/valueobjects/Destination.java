package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Destination(@NotBlank String value) {
    public Destination() {
        this(null);
    }

    public Destination {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Destination value cannot be null or blank");
        }
    }
}
