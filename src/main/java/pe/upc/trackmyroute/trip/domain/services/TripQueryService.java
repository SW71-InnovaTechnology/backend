package pe.upc.trackmyroute.trip.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.trip.domain.model.aggregates.Trip;
import pe.upc.trackmyroute.trip.domain.model.queries.GetAllTripsQuery;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByIdQuery;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByOriginQuery;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByFareQuery;

import java.util.List;
import java.util.Optional;

@Service
public interface TripQueryService {
    Optional<Trip> handle(GetTripByIdQuery query);
    Optional<Trip> handle(GetTripByOriginQuery query);
    Optional<Trip> handle(GetTripByFareQuery query);
    List<Trip> handle(GetAllTripsQuery query);
}
