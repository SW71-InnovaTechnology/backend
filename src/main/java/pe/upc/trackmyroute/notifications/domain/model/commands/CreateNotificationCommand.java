package pe.upc.trackmyroute.notifications.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateNotificationCommand(
        @NotBlank String title,
        @NotBlank String subtitle,
        @NotBlank String description,
        @NotBlank String imgUrl
) {
}
