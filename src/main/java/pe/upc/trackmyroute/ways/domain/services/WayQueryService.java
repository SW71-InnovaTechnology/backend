package pe.upc.trackmyroute.ways.domain.services;

import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWaysQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWayByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WayQueryService {
    List<Way> handle(GetAllWaysQuery query);
    Optional<Way> handle(GetWayByIdQuery query);
}
