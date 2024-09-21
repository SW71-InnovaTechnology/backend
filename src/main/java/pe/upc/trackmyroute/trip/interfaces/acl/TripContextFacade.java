package pe.upc.trackmyroute.trip.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.trip.domain.model.commands.CreateTripCommand;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByFareQuery;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByIdQuery;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByOriginQuery;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Fare;
import pe.upc.trackmyroute.trip.domain.model.valueobjects.Origin;
import pe.upc.trackmyroute.trip.domain.services.TripCommandService;
import pe.upc.trackmyroute.trip.domain.services.TripQueryService;

@Service
public class TripContextFacade {
    private final TripCommandService tripCommandService;
    private final TripQueryService tripQueryService;

    public TripContextFacade(TripCommandService tripCommandService, TripQueryService tripQueryService) {
        this.tripCommandService = tripCommandService;
        this.tripQueryService = tripQueryService;
    }

    public Long createTrip(String origin,
                           String destination,
                           String time,
                           String fare) {
        var createTripCommand = new CreateTripCommand(origin, destination, time, fare);
        var trip = tripCommandService.handle(createTripCommand);
        if (trip.isEmpty()) return 0L;
        return trip.get().getId();
    }

    public Long fetchTripIdByFare(String fare) {
        var getTripByFareQuery = new GetTripByFareQuery(new Fare(fare));
        // Handle the query using tripQueryService
        return 0L; // Placeholder return value
    }

    public Long fetchTripIdByOrigin(String origin) {
        var getTripByOriginQuery = new GetTripByOriginQuery(new Origin(origin));
        // Handle the query using tripQueryService
        return 0L; // Placeholder return value
    }

    public Long fetchTripIdById(Long id) {
        var getTripByIdQuery = new GetTripByIdQuery(id);
        // Handle the query using tripQueryService
        return 0L; // Placeholder return value
    }
}
