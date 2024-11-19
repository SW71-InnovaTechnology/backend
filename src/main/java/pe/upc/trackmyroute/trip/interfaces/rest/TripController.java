package pe.upc.trackmyroute.trip.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.trip.domain.model.aggregates.Trip;
import pe.upc.trackmyroute.trip.domain.model.commands.CreateTripCommand;
import pe.upc.trackmyroute.trip.domain.model.queries.GetTripByIdQuery;
import pe.upc.trackmyroute.trip.domain.services.TripCommandService;
import pe.upc.trackmyroute.trip.domain.services.TripQueryService;
import pe.upc.trackmyroute.trip.interfaces.rest.resources.CreateTripResource;
import pe.upc.trackmyroute.trip.interfaces.rest.resources.TripResource;
import pe.upc.trackmyroute.trip.interfaces.rest.transform.CreateTripCommandFromResourceAssembler;
import pe.upc.trackmyroute.trip.interfaces.rest.transform.TripResourceFromEntityAssembler;
import pe.upc.trackmyroute.trip.domain.model.queries.GetAllTripsQuery;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/trips")
@Tag(name="Trips", description = "Trip Management Endpoints")
public class TripController {

    private final TripQueryService tripQueryService;
    private final TripCommandService tripCommandService;

    public TripController(TripQueryService tripQueryService, TripCommandService tripCommandService) {
        this.tripQueryService = tripQueryService;
        this.tripCommandService = tripCommandService;
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResource> getTripById(@PathVariable Long tripId) {
        var getTripByIdQuery = new GetTripByIdQuery(tripId);
        var trip = tripQueryService.handle(getTripByIdQuery);
        if (trip.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TripResourceFromEntityAssembler.transformResourceFromEntity(trip.get()));
    }

    @GetMapping
    public ResponseEntity<List<TripResource>> getAllResources(){
        var getAllTripsQuery = new GetAllTripsQuery();
        var trips = tripQueryService.handle(getAllTripsQuery);
        var tripResources = trips.stream().map(TripResourceFromEntityAssembler::transformResourceFromEntity).toList();
        return ResponseEntity.ok(tripResources);
    }

    @PostMapping
    public ResponseEntity<TripResource> createTrip(@RequestBody CreateTripResource resource) {
        var createTripCommand = CreateTripCommandFromResourceAssembler.toCommandFromResource(resource);
        var trip = tripCommandService.handle(createTripCommand);

        if (trip.isEmpty()) return ResponseEntity.badRequest().build();

        var tripResource = TripResourceFromEntityAssembler.transformResourceFromEntity(trip.get());

        return new ResponseEntity<>(tripResource, HttpStatus.CREATED);
    }
}
