package pe.upc.trackmyroute.buses.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.trackmyroute.buses.domain.model.aggregates.Bus;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stop_name")
    private String stopName;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;
}