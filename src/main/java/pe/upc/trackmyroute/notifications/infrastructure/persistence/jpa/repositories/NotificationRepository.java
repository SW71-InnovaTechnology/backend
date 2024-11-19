package pe.upc.trackmyroute.notifications.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.trackmyroute.notifications.domain.model.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
