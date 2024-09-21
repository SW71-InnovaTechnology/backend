package pe.upc.trackmyroute.trip.domain.model.aggregates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.trackmyroute.trip.domain.model.commands.CreateTripCommand;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Destination;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Fare;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Origin;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Time;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends AuditableAbstractAggregateRoot<Trip> {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "origin_value"))
    Origin origin;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "destination_value"))
    Destination destination;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "time_value"))
    Time time;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "fare_value"))
    Fare fare;

    public Trip(CreateTripCommand command) {
        this.origin = new Origin(command.origin());
        this.destination = new Destination(command.destination());
        this.time = new Time(command.time());
        this.fare = new Fare(command.fare());
    }

    public String getOrigin(){
        return origin.value();
    }

    public String getTime(){
        return time.value();
    }

    public String getFare(){
        return fare.value();
    }

    public String getDestination(){
        return destination.value();
    }

}
