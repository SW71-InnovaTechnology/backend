package pe.upc.trackmyroute.notifications.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.commands.DeleteNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationByIdQuery;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationsQuery;
import pe.upc.trackmyroute.notifications.domain.services.NotificationCommandService;
import pe.upc.trackmyroute.notifications.domain.services.NotificationQueryService;
import pe.upc.trackmyroute.notifications.interfaces.rest.resources.CreateNotificationResource;
import pe.upc.trackmyroute.notifications.interfaces.rest.resources.NotificationResource;
import pe.upc.trackmyroute.notifications.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import pe.upc.trackmyroute.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/notifications")
@Tag(name="Notifications", description = "Notification Management Endpoints")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var notifications = notificationQueryService.handle(new GetNotificationsQuery());
        var notificationsResource = notifications.stream().map(NotificationResourceFromEntityAssembler::transformResourceFromEntity).toList();
        return ResponseEntity.ok(notificationsResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long id) {
        var notification = notificationQueryService.handle(new GetNotificationByIdQuery(id));
        if (notification.isEmpty()) {
            throw new IllegalArgumentException("Notification with id " + id + " not found");
        }
        var notificationResource = NotificationResourceFromEntityAssembler.transformResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resource);
        var notification = notificationCommandService.handle(createNotificationCommand);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.transformResourceFromEntity(notification.get());
        return new ResponseEntity<NotificationResource>(notificationResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationResource> deleteNotificationById(@PathVariable Long id) {
        var deleteNotificationCommand = new DeleteNotificationCommand(id);
        var notification = notificationCommandService.handle(deleteNotificationCommand);

        if (notification.isEmpty()) return ResponseEntity.badRequest().build();

        var notificationResource = NotificationResourceFromEntityAssembler.transformResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }
}
