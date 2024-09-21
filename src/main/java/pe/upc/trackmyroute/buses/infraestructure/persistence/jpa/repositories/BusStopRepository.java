package pe.upc.trackmyroute.buses.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.trackmyroute.buses.domain.model.entities.BusStop;

import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    List<BusStop> findByBus_Id(Long busId);
}