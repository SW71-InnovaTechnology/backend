package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Time(@NotBlank String value) {
    public Time() {
        this(null);
    }

    public Time {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Time value cannot be null or blank");
        }
        // Aquí podrías agregar validaciones adicionales para asegurarte de que el formato de la hora sea correcto, etc.
    }

}
