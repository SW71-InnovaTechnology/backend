package pe.upc.trackmyroute.ways.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWaysQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWayByIdQuery;
import pe.upc.trackmyroute.ways.domain.services.WayQueryService;
import pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories.WayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WayQueryServiceImpl implements WayQueryService {

    private final WayRepository wayRepository;

    public WayQueryServiceImpl(WayRepository wayRepository) {
        this.wayRepository = wayRepository;
    }

    @Override
    public List<Way> handle(GetAllWaysQuery query) {
        return wayRepository.findAll();
    }

    @Override
    public Optional<Way> handle(GetWayByIdQuery query) {
        return wayRepository.findById(query.wayId());
    }
}
