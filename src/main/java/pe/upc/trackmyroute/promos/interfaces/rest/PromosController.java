package pe.upc.trackmyroute.promos.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.promos.application.internal.PromoQueryServiceImpl;
import pe.upc.trackmyroute.promos.domain.model.commands.DeletePromoCommand;
import pe.upc.trackmyroute.promos.domain.model.entities.Promo;
import pe.upc.trackmyroute.promos.domain.model.queries.GetPromosQuery;
import pe.upc.trackmyroute.promos.domain.services.PromoQueryService;
import pe.upc.trackmyroute.promos.interfaces.rest.resources.CreatePromoResource;
import pe.upc.trackmyroute.promos.interfaces.rest.resources.PromoResource;
import pe.upc.trackmyroute.promos.interfaces.rest.transform.CreatePromoCommandFromResourceAssembler;
import pe.upc.trackmyroute.promos.domain.services.PromoCommandService;
import pe.upc.trackmyroute.promos.interfaces.rest.transform.PromoResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/promos")
@Tag(name="Promos", description = "Promos Management Endpoints")
public class PromosController {

    private final PromoQueryService promoQueryService;
    private final PromoCommandService promoCommandService;

    public PromosController(PromoQueryService promoQueryService, PromoCommandService promoCommandService) {
        this.promoQueryService = promoQueryService;
        this.promoCommandService = promoCommandService;
    }

    @GetMapping
    public ResponseEntity<List<Promo>> getAllPromos() {
        var promos = promoQueryService.handle(new GetPromosQuery());
        return ResponseEntity.ok(promos);
    }

    @PostMapping
    public ResponseEntity<PromoResource> createPromo(@RequestBody CreatePromoResource createPromoResource) {
        var createPromoCommand = CreatePromoCommandFromResourceAssembler.toCommandFromResource(createPromoResource);
        var promo = promoCommandService.handle(createPromoCommand);

        if(promo.isEmpty()) return ResponseEntity.badRequest().build();

        var promoResource = PromoResourceFromEntityAssembler.transformResourceFromEntity(promo.get());
        return new ResponseEntity<PromoResource>(promoResource, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<PromoResource> deletePromo(@RequestParam Long promoId) {
        var deletePromoCommand = new DeletePromoCommand(promoId);
        var promo = promoCommandService.handle(deletePromoCommand);

        if(promo.isEmpty()) return ResponseEntity.badRequest().build();

        var promoResource = PromoResourceFromEntityAssembler.transformResourceFromEntity(promo.get());
        return new ResponseEntity<PromoResource>(promoResource, HttpStatus.CREATED);
    }
}
