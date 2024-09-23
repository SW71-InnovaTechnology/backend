package pe.upc.trackmyroute.notifications.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationByIdQuery;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationsQuery;

import java.util.List;
import java.util.Optional;

@Service
public interface NotificationQueryService {
    Optional<Notification> handle(GetNotificationByIdQuery query);
    List<Notification> handle(GetNotificationsQuery query);
}
