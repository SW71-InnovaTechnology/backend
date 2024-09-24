package pe.upc.trackmyroute.notifications.interfaces.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;
import pe.upc.trackmyroute.notifications.domain.model.queries.GetNotificationsQuery;
import pe.upc.trackmyroute.notifications.domain.services.NotificationCommandService;
import pe.upc.trackmyroute.notifications.domain.services.NotificationQueryService;
import pe.upc.trackmyroute.notifications.interfaces.rest.resources.CreateNotificationResource;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private NotificationCommandService notificationCommandService;

    @Mock
    private NotificationQueryService notificationQueryService;

    private Notification notification;
    private Notification prueba;
    private String token;

    @BeforeEach
    void setUp() {
        notification = new Notification(new CreateNotificationCommand(
                "¡Alerta de Tráfico en la Ruta 32!",
                "Retraso en el Servicio de Micros",
                "Debido a obras en la Av. Javier Prado, los micros de la Ruta 32 tendrán retrasos de aproximadamente 20 minutos. Se recomienda tomar vías alternas o anticipar su salida.",
                "https://i0.wp.com/www.vozactual.com/wp-content/uploads/2016/09/rsz_i1.jpg?fit=800%2C445&ssl=1"
        ));
        notification.setId(3L);
        prueba = new Notification(new CreateNotificationCommand(
                "PRUEBA",
                "PRUEBA",
                "PRUEBA",
                "PRUEBA"
        ));
        token = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJnaW92YW5uaSIsImlhdCI6MTcyNzIxNDg3MiwiZXhwIjoxNzI3ODE5NjcyfQ.J_NioE7j1v7GrxmUkibw3sqoU-hymc9iPGQdvxCV0n9ERZfqwSwLRxJYoP_MkMa1";
    }

    @Test
    void getAllNotifications() throws Exception {
        // Arrange
        when(notificationQueryService.handle(any(GetNotificationsQuery.class)))
                .thenReturn(Collections.singletonList(notification));

        // Act & Assert
        mockMvc.perform(get("/api/v1/notifications").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(notification.getId()))
                .andExpect(jsonPath("$[0].title").value(notification.getTitle()))
                .andExpect(jsonPath("$[0].subtitle").value(notification.getSubtitle()))
                .andExpect(jsonPath("$[0].description").value(notification.getDescription()))
                .andExpect(jsonPath("$[0].img_url").value(notification.getImgUrl()));
    }

    @Test
    void getNotificationById() throws Exception{
        when(notificationQueryService.handle(any(GetNotificationsQuery.class)))
                .thenReturn(Collections.singletonList(notification));

        // Act & Assert
        mockMvc.perform(get("/api/v1/notifications/3").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notification.getId()))
                .andExpect(jsonPath("$.title").value(notification.getTitle()))
                .andExpect(jsonPath("$.subtitle").value(notification.getSubtitle()))
                .andExpect(jsonPath("$.description").value(notification.getDescription()))
                .andExpect(jsonPath("$.img_url").value(notification.getImgUrl()));
    }

    @Test
    void createNotification() throws Exception {
        var resource = new CreateNotificationResource(
                prueba.getTitle(),
                prueba.getSubtitle(),
                prueba.getDescription(),
                prueba.getImgUrl()
        );

        when(notificationQueryService.handle(any(GetNotificationsQuery.class)))
                .thenReturn(Collections.singletonList(notification));

        // Act & Assert
        mockMvc.perform(post("/api/v1/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource))
                        .header("Authorization", token))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(prueba.getTitle()))
                .andExpect(jsonPath("$.subtitle").value(prueba.getSubtitle()))
                .andExpect(jsonPath("$.description").value(prueba.getDescription()))
                .andExpect(jsonPath("$.img_url").value(prueba.getImgUrl()));
    }
}