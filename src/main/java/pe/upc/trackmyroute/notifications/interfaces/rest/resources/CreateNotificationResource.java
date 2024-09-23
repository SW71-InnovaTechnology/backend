package pe.upc.trackmyroute.notifications.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateNotificationResource(
        @NotBlank String title,
        @NotBlank String subtitle,
        @NotBlank String description,
        @NotBlank String img_url
) {
}
