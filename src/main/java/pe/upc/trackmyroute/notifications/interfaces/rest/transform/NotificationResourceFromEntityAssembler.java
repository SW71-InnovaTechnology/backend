package pe.upc.trackmyroute.notifications.interfaces.rest.transform;

import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource transformResourceFromEntity(Notification notification) {
        return new NotificationResource(
                notification.getId(),
                notification.getTitle(),
                notification.getSubtitle(),
                notification.getDescription(),
                notification.getImgUrl()
        );
    }
}
