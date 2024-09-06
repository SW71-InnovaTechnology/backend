package pe.upc.trackmyroute.promos.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.trackmyroute.promos.domain.model.entities.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
