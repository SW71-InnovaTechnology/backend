package pe.upc.trackmyroute.promos.interfaces.rest.transform;

import pe.upc.trackmyroute.promos.domain.model.commands.CreatePromoCommand;
import pe.upc.trackmyroute.promos.interfaces.rest.resources.CreatePromoResource;

public class CreatePromoCommandFromResourceAssembler {
    public static CreatePromoCommand toCommandFromResource(CreatePromoResource resource){
        return new CreatePromoCommand(
                resource.name(),
                resource.description(),
                resource.imageUrl(),
                resource.validUntil()
        );
    }
}
