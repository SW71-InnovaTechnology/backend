package pe.upc.trackmyroute.buses.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.trackmyroute.buses.domain.model.aggregates.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
