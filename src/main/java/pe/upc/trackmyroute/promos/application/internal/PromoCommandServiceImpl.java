package pe.upc.trackmyroute.promos.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.promos.domain.model.commands.CreatePromoCommand;
import pe.upc.trackmyroute.promos.domain.model.commands.DeletePromoCommand;
import pe.upc.trackmyroute.promos.domain.model.entities.Promo;
import pe.upc.trackmyroute.promos.domain.services.PromoCommandService;
import pe.upc.trackmyroute.promos.infrastructure.persistence.jpa.repositories.PromoRepository;

import java.util.Optional;

@Service
public class PromoCommandServiceImpl implements PromoCommandService {
    private final PromoRepository promoRepository;

    public PromoCommandServiceImpl(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    @Override
    public Optional<Promo> handle(CreatePromoCommand command) {
        var promo = new Promo(command);
        promoRepository.save(promo);
        return Optional.of(promo);
    }

    @Override
    public Optional<Promo> handle(DeletePromoCommand command) {
        var promo = promoRepository.findById(command.id());
        if (promo.isEmpty()) {
            throw new IllegalArgumentException("Promo with " + command.id() + " id not found");
        }
        promoRepository.delete(promo.get());
        return Optional.of(promo.get());
    }
}
