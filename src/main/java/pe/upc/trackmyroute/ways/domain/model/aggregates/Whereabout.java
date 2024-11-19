package pe.upc.trackmyroute.ways.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWhereaboutsCommand;

@Getter
@Entity
public class Whereabout extends AuditableAbstractAggregateRoot<Whereabout> {
    private String latitude;
    private String longitude;
    @ManyToOne
    private Way way;

    public Whereabout() {
        this.latitude = Strings.EMPTY;
        this.longitude = Strings.EMPTY;
    }

    public Whereabout(String latitude, String longitude, Way way) {
        this();
        this.latitude = latitude;
        this.longitude = longitude;
        this.way = way;
    }
    public Whereabout(CreateWhereaboutsCommand command, Way way){
        this();
        this.latitude = command.getLatitude();
        this.longitude = command.getLongitude();
        this.way = way;
    }
    public Whereabout updateInformation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }
}
