package pe.upc.trackmyroute.notifications.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;
import pe.upc.trackmyroute.notifications.domain.model.commands.CreateNotificationCommand;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Notification extends AuditableAbstractAggregateRoot<Notification> {
    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    public Notification(CreateNotificationCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.subtitle = command.subtitle();
        this.imgUrl = command.imgUrl();
    }
}
