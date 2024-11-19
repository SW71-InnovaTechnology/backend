package pe.upc.trackmyroute.notifications.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.domain.services.NotificationCommandService;
import pe.upc.trackmyroute.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        var notification = new Notification(command);
        notificationRepository.save(notification);
        return Optional.of(notification);
    }

    @Override
    public Optional<Notification> handle(DeleteNotificationCommand command) {
        var notification = notificationRepository.findById(command.id());
        if (notification.isEmpty()) {
            throw new IllegalArgumentException("Notification with id " + command.id() + " not found");
        }
        notificationRepository.delete(notification.get());
        return notification;
    }
}
