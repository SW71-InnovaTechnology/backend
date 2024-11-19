package pe.upc.trackmyroute.ways.domain.services;



import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWhereaboutsQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWhereaboutByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WhereaboutQueryService {
    List<Whereabout> handle(GetAllWhereaboutsQuery query);
    Optional<Whereabout> handle(GetWhereaboutByIdQuery query);
}
