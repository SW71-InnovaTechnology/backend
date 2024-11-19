package pe.upc.trackmyroute.ways.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.ways.application.internal.commandservices.WayCommandServiceImpl;
import pe.upc.trackmyroute.ways.application.internal.queryservices.WayQueryServiceImpl;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWayCommand;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWaysQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWayByIdQuery;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.CreateWayResource;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.UpdateWayResource;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.WayResource;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.CreateWayCommandFromResourceAssembler;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.UpdateWayCommandFromResourceAssembler;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.WayResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value= "/api/v1/ways", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Ways", description = "Ways Management Endpoints")
public class WayController {

    private final WayCommandServiceImpl wayCommandService;
    private final WayQueryServiceImpl wayQueryService;

    public WayController(WayCommandServiceImpl wayCommandService, WayQueryServiceImpl wayQueryService) {
        this.wayCommandService = wayCommandService;
        this.wayQueryService = wayQueryService;
    }

    @PostMapping
    public ResponseEntity<WayResource> createWay(@RequestBody CreateWayResource createWayResource) {
        var createWayCommand = CreateWayCommandFromResourceAssembler.toCommandFromResource(createWayResource);
        var wayId = wayCommandService.handle(createWayCommand);
        if(wayId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getWayByIdQuery = new GetWayByIdQuery(wayId);
        var way = wayQueryService.handle(getWayByIdQuery);
        if(way.isEmpty()) {return ResponseEntity.badRequest().build();}
        var wayResource = WayResourceFromEntityAssembler.toResourceFromEntity(way.get());
        return new ResponseEntity<>(wayResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{wayId}")
    public ResponseEntity<?> deleteWay(@PathVariable Long wayId) {
        var deleteWayCommand = new DeleteWayCommand(wayId);
        wayCommandService.handle(deleteWayCommand);
        return ResponseEntity.ok("Way with id " + wayId + " deleted");
    }

    @PutMapping ("/{wayId}")
    public ResponseEntity<WayResource> updateWay(@PathVariable Long wayId, @RequestBody UpdateWayResource updateWayResource) {
        var updateWayCommand = UpdateWayCommandFromResourceAssembler.toCommandFromResource(wayId,updateWayResource);
        var updateWay = wayCommandService.handle(updateWayCommand);
        if(updateWay.isEmpty()) {return ResponseEntity.badRequest().build();}
        var wayResource = WayResourceFromEntityAssembler.toResourceFromEntity(updateWay.get());
        return ResponseEntity.ok(wayResource);
    }

    @GetMapping
    public ResponseEntity<List<WayResource>> getAllWays() {
        var getAllWaysQuery = new GetAllWaysQuery();
        var ways = wayQueryService.handle(getAllWaysQuery);
        var wayResources = ways.stream().map(WayResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(wayResources);
    }
    @GetMapping("/{wayId}")
    public ResponseEntity<WayResource> getWayById(@PathVariable Long wayId) {
        var getWayByIdQuery = new GetWayByIdQuery(wayId);
        var way = wayQueryService.handle(getWayByIdQuery);
        if(way.isEmpty()) {return ResponseEntity.notFound().build();}
        var wayResource = WayResourceFromEntityAssembler.toResourceFromEntity(way.get());
        return ResponseEntity.ok(wayResource);
    }
}
