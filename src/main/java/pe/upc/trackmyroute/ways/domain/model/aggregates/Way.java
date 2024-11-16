package pe.upc.trackmyroute.ways.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import pe.upc.trackmyroute.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWayCommand;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Way extends AuditableAbstractAggregateRoot<Way> {
    private String startLatitude;
    private String startLongitude;
    private String endLatitude;
    private String endLongitude;

    @OneToMany(mappedBy = "way")
    private List<Whereabout> whereabout= new ArrayList<>();

    public Way(){
        this.startLatitude = "";
        this.startLongitude = "";
        this.endLatitude = "";
        this.endLongitude = "";
    }

    public Way(String startLatitude, String startLongitude, String endLatitude, String endLongitude, List<Whereabout> whereabout){
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.whereabout = whereabout;
    }
    public Way(CreateWayCommand command){
        this();
        this.startLatitude = command.startLatitude();
        this.startLongitude = command.startLongitude();
        this.endLatitude = command.endLatitude();
        this.endLongitude = command.endLongitude();
    }
    public Way updateInformation(String startLatitude, String startLongitude, String endLatitude, String endLongitude){
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        return this;
    }
}
