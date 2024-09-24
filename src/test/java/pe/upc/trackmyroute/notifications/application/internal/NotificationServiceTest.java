package pe.upc.trackmyroute.notifications.application.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.upc.trackmyroute.iam.domain.model.valueobjects.Roles;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationCommandServiceImpl notificationCommandService;

    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification(new CreateNotificationCommand(
                "NOTIFICACION PRUEBA",
                "SUBTITULO NOTIFICACION PRUEBA",
                "DESCRIPCION NOTIFICACION PRUEBA",
                "URL PRUEBA"
        ));

    }

    @Test
    void createNotification() {
        // Arrange
        CreateNotificationCommand command = new CreateNotificationCommand(
                "NOTIFICACION PRUEBA",
                "SUBTITULO NOTIFICACION PRUEBA",
                "DESCRIPCION NOTIFICACION PRUEBA",
                "URL PRUEBA"
        );

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // Act
        Optional<Notification> result = notificationCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(notification.getTitle(), result.get().getTitle());
        assertEquals(notification.getSubtitle(), result.get().getSubtitle());
        assertEquals(notification.getDescription(), result.get().getDescription());
    }

    @Test
    void deleteNotification() {
        // Arrange
        DeleteNotificationCommand command = new DeleteNotificationCommand(1L);
        when(notificationRepository.findById(command.id())).thenReturn(Optional.of(notification));

        // Act
        Optional<Notification> result = notificationCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(notification.getTitle(), result.get().getTitle());
        assertEquals(notification.getSubtitle(), result.get().getSubtitle());
        assertEquals(notification.getDescription(), result.get().getDescription());
    }
}
