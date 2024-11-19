package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.commands.CreateWhereaboutsCommand;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.CreateWhereaboutResource;

public class CreateWhereaboutCommandFromResourceAssembler {
    public static CreateWhereaboutsCommand toCommandFromResource(CreateWhereaboutResource resource) {
        return new CreateWhereaboutsCommand(
                resource.wayId(),
                resource.latitude(),
                resource.longitude()
        );
    }
}
