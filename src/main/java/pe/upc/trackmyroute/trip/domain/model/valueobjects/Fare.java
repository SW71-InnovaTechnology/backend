package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Fare(@NotBlank String value) {
    public Fare() {
        this(null);
    }

    public Fare {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Fare value cannot be null or blank");
        }
        // Aquí podrías agregar validaciones adicionales para asegurarte de que el formato del precio sea correcto, etc.
    }
}
