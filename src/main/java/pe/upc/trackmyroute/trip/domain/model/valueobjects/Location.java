package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Location(
        @NotBlank String latitude,
        @NotBlank String longitude
) {
    public Location() {
        this(null, null);
    }

    public Location {
        if (latitude == null || latitude.isBlank()) {
            throw new IllegalArgumentException("Latitude cannot be null or blank");
        }
        if (longitude == null || longitude.isBlank()) {
            throw new IllegalArgumentException("Longitude cannot be null or blank");
        }
    }

    public String getCoordinates() {
        return String.format("(%s, %s)", latitude, longitude);
    }
}