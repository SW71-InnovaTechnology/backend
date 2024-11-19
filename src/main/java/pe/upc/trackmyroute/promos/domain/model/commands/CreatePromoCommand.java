package pe.upc.trackmyroute.promos.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CreatePromoCommand(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String imageUrl,
        @NotBlank Date validUntil
) {
}
