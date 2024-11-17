package pe.upc.trackmyroute.ways.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWhereaboutsQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWhereaboutByIdQuery;
import pe.upc.trackmyroute.ways.domain.services.WhereaboutQueryService;
import pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories.WhereaboutRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WhereaboutQueryServiceImpl implements WhereaboutQueryService {

    private final WhereaboutRepository whereaboutRepository;

    public WhereaboutQueryServiceImpl(WhereaboutRepository whereaboutRepository) {
        this.whereaboutRepository = whereaboutRepository;
    }

    @Override
    public List<Whereabout> handle(GetAllWhereaboutsQuery query) {
        return whereaboutRepository.findAll();
    }

    @Override
    public Optional<Whereabout> handle(GetWhereaboutByIdQuery query) {
        return whereaboutRepository.findById(query.whereaboutId());
    }
}
