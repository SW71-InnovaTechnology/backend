package pe.upc.trackmyroute.buses.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.trackmyroute.buses.domain.model.entities.BusStop;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "busName_value"))
    private String busName;


    @AttributeOverride(name = "value", column = @Column(name = "busStops_values"))
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusStop> busStops;
}
