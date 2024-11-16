package pe.upc.trackmyroute.ways.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.model.queries.GetAllWhereaboutsQuery;
import pe.upc.trackmyroute.ways.domain.model.queries.GetWhereaboutByIdQuery;
import pe.upc.trackmyroute.ways.domain.services.WhereaboutCommandService;
import pe.upc.trackmyroute.ways.domain.services.WhereaboutQueryService;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.CreateWhereaboutResource;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.UpdateWhereaboutResource;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.WhereaboutResource;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.CreateWhereaboutCommandFromResourceAssembler;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.UpdateWhereaboutCommandFromResourceAssembler;
import pe.upc.trackmyroute.ways.interfaces.rest.transform.WhereaboutResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/whereabouts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Whereabouts", description = "Whereabouts Management Endpoints")
public class WhereaboutController {
    private final WhereaboutCommandService whereaboutCommandService;
    private final WhereaboutQueryService whereaboutQueryService;

    public WhereaboutController(WhereaboutCommandService whereaboutCommandService, WhereaboutQueryService whereaboutQueryService) {
        this.whereaboutCommandService = whereaboutCommandService;
        this.whereaboutQueryService = whereaboutQueryService;
    }

    @PostMapping
    public ResponseEntity<WhereaboutResource> createWhereabout(@RequestBody CreateWhereaboutResource createWhereaboutResource) {
        var createWhereaboutCommand = CreateWhereaboutCommandFromResourceAssembler.toCommandFromResource(createWhereaboutResource);
        var whereaboutId = whereaboutCommandService.handle(createWhereaboutCommand);
        if(whereaboutId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getWhereaboutByIdQuery = new GetWhereaboutByIdQuery(whereaboutId);
        var whereabout = whereaboutQueryService.handle(getWhereaboutByIdQuery);
        if(whereabout.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var whereaboutResource = WhereaboutResourceFromEntityAssembler.toResourceFromEntity(whereabout.get());
        return new ResponseEntity<>(whereaboutResource, HttpStatus.CREATED);
    }

    @GetMapping("/{whereaboutId}")
    public ResponseEntity<WhereaboutResource> getWhereaboutById(@PathVariable Long whereaboutId) {
        var getWhereaboutByIdQuery = new GetWhereaboutByIdQuery(whereaboutId);
        var whereabout = whereaboutQueryService.handle(getWhereaboutByIdQuery);
        if(whereabout.isEmpty()) {return ResponseEntity.notFound().build();}
        var whereaboutResource = WhereaboutResourceFromEntityAssembler.toResourceFromEntity(whereabout.get());
        return ResponseEntity.ok(whereaboutResource);
    }

    @GetMapping
    public ResponseEntity<List<WhereaboutResource>> getAllWhereabouts() {
        var getAllWhereaboutsQuery = new GetAllWhereaboutsQuery();
        var whereabouts = whereaboutQueryService.handle(getAllWhereaboutsQuery);
        var whereaboutResources = whereabouts.stream().map(WhereaboutResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(whereaboutResources);
    }

    @PutMapping("/{whereaboutId}")
    public ResponseEntity<WhereaboutResource> updateWhereabout(@PathVariable Long whereaboutId, @RequestBody UpdateWhereaboutResource updateWhereaboutResource) {
        var updateWhereaboutCommand = UpdateWhereaboutCommandFromResourceAssembler.toCommandFromResource(whereaboutId, updateWhereaboutResource);
        var updateWhereabout = whereaboutCommandService.handle(updateWhereaboutCommand);
        if(updateWhereabout.isEmpty()) {return ResponseEntity.notFound().build();}
        var whereaboutResource = WhereaboutResourceFromEntityAssembler.toResourceFromEntity(updateWhereabout.get());
        return ResponseEntity.ok(whereaboutResource);
    }

    @DeleteMapping("/{whereaboutId}")
    public ResponseEntity<Void> deleteWhereabout(@PathVariable Long whereaboutId) {
        var deleteWhereaboutCommand = new DeleteWhereaboutsCommand(whereaboutId);
        whereaboutCommandService.handle(deleteWhereaboutCommand);
        return ResponseEntity.noContent().build();
    }
}
