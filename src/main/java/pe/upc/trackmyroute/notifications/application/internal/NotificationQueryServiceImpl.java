package pe.upc.trackmyroute.notifications.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationByIdQuery;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationsQuery;
import pe.upc.trackmyroute.notifications.domain.services.NotificationQueryService;
import pe.upc.trackmyroute.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }

    @Override
    public List<Notification> handle(GetNotificationsQuery query) {
        return notificationRepository.findAll();
    }
}
