package pe.upc.trackmyroute.promos.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.promos.domain.model.commands.CreatePromoCommand;
import pe.upc.trackmyroute.promos.domain.model.entities.Promo;

import java.util.Optional;

@Service
public interface PromoCommandService {
    Optional<Promo> handle(CreatePromoCommand command);
}
