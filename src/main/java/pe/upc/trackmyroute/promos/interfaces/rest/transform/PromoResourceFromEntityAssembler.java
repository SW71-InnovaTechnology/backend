package pe.upc.trackmyroute.promos.interfaces.rest.transform;

import pe.upc.trackmyroute.promos.domain.model.entities.Promo;
import pe.upc.trackmyroute.promos.interfaces.rest.resources.PromoResource;

public class PromoResourceFromEntityAssembler {
    public static PromoResource transformResourceFromEntity(Promo entity){
        return new PromoResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getValidUntil());
    }
}
