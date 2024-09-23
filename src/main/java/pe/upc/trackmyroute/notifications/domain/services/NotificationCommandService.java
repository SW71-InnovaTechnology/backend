package pe.upc.trackmyroute.notifications.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;

import java.util.Optional;

@Service
public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    Optional<Notification> handle(DeleteNotificationCommand command);
}
