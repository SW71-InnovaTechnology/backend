package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWhereaboutsCommand;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.UpdateWhereaboutResource;

public class UpdateWhereaboutCommandFromResourceAssembler {
    public static UpdateWhereaboutsCommand toCommandFromResource(Long whereaboutId, UpdateWhereaboutResource resource) {
        return new UpdateWhereaboutsCommand(
                whereaboutId,
                resource.Latitude(),
                resource.Longitude()
        );
    }
}
