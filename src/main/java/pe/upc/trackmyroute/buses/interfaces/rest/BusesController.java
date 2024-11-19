package pe.upc.trackmyroute.buses.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.trackmyroute.buses.domain.model.aggregates.Bus;
import pe.upc.trackmyroute.buses.domain.model.queries.GetAllBusesQuery;
import pe.upc.trackmyroute.buses.domain.model.queries.GetBusByIdQuery;
import pe.upc.trackmyroute.buses.domain.services.BusesQueryService;
import pe.upc.trackmyroute.buses.interfaces.rest.resources.BusesResource;
import pe.upc.trackmyroute.buses.interfaces.rest.transform.BusesResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/buses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Buses", description = "Buses Management Endpoints")
public class BusesController {
    private final BusesQueryService busesQueryService;

    public BusesController(BusesQueryService busesQueryService) {
        this.busesQueryService = busesQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        var buses = busesQueryService.handle(new GetAllBusesQuery());
        if (buses.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(buses);
    }


    @GetMapping("/{bus_id}")
    public ResponseEntity<BusesResource> getBusById(@PathVariable Long bus_id) {
        var getBusByIdQuery = new GetBusByIdQuery(bus_id);
        var bus = busesQueryService.handle(getBusByIdQuery);
        if(bus.isEmpty()) return ResponseEntity.badRequest().build();
        var busResource = BusesResourceFromEntityAssembler.toResourceFromEntity(bus.get());
        return ResponseEntity.ok(busResource);
    }
}
