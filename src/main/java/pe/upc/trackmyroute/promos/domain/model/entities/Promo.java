package pe.upc.trackmyroute.promos.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "promos")
@Getter
@Setter
public class Promo extends AuditableAbstractAggregateRoot<Promo> {
    private String name;
    private String description;
    private String imageUrl;
    private Date validUntil;
}