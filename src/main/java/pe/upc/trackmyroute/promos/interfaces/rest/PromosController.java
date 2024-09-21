package pe.upc.trackmyroute.promos.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.promos.application.internal.PromoQueryServiceImpl;
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

    public PromosController(PromoQueryService promoQueryService) {
        this.promoQueryService = promoQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Promo>> getAllPromos() {
        var promos = promoQueryService.handle(new GetPromosQuery());
        return ResponseEntity.ok(promos);
    }
}
