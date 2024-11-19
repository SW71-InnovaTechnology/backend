package pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;

import java.util.Optional;

@Repository
public interface WhereaboutRepository extends JpaRepository<Whereabout, Long> {
    Optional<Whereabout> findByLatitudeAndLongitude(String latitude, String longitude);
    boolean existsByLatitudeAndLongitude(String latitude, String longitude);
}
