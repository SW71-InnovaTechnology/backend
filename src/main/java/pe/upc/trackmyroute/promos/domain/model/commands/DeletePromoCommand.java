package pe.upc.trackmyroute.promos.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeletePromoCommand(
        @NotBlank Long id
) {
}
