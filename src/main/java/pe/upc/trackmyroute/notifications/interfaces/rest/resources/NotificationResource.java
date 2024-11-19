package pe.upc.trackmyroute.notifications.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record NotificationResource(
        Long id,
        String title,
        String subtitle,
        String description,
        String img_url
) {
}
