package pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;

import java.util.Optional;

@Repository
public interface WayRepository extends JpaRepository<Way, Long> {
    Optional<Way> findByStartLatitudeAndStartLongitude(String startLatitude, String startLongitude);
    boolean existsByStartLatitudeAndStartLongitude(String startLatitude, String startLongitude);
    boolean existsByEndLatitudeAndEndLongitude(String endLatitude, String endLongitude);
}
