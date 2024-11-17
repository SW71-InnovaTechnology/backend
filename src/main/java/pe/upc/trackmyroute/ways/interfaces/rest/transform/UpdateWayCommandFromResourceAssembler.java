package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWayCommand;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.UpdateWayResource;

public class UpdateWayCommandFromResourceAssembler {
    public static UpdateWayCommand toCommandFromResource(Long wayId, UpdateWayResource resource) {
        return new UpdateWayCommand(
                wayId,
                resource.startLatitude(),
                resource.startLongitude(),
                resource.endLatitude(),
                resource.endLongitude()
        );
    }
}
