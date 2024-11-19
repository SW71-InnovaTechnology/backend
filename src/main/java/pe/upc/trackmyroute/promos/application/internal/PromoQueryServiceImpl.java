package pe.upc.trackmyroute.promos.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.promos.domain.model.entities.Promo;
import pe.upc.trackmyroute.promos.domain.model.queries.GetPromoByIdQuery;
import pe.upc.trackmyroute.promos.domain.model.queries.GetPromosQuery;
import pe.upc.trackmyroute.promos.domain.services.PromoQueryService;
import pe.upc.trackmyroute.promos.infrastructure.persistence.jpa.repositories.PromoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PromoQueryServiceImpl implements PromoQueryService {

    private final PromoRepository promoRepository;

    public PromoQueryServiceImpl(PromoRepository promoRepository) {

        this.promoRepository = promoRepository;
    }

    public List<Promo> getAllPromos() {
        return promoRepository.findAll();
    }

    @Override
    public Optional<Promo> handle(GetPromoByIdQuery query) {
        return promoRepository.findById(query.id());
    }

    @Override
    public List<Promo> handle(GetPromosQuery query) {
        return promoRepository.findAll();
    }
}
