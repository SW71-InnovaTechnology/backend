package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.commands.CreateWayCommand;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.CreateWayResource;

public class CreateWayCommandFromResourceAssembler {
    public static CreateWayCommand toCommandFromResource(CreateWayResource resource) {
        return new CreateWayCommand(
                resource.startLatitude(),
                resource.startLongitude(),
                resource.endLatitude(),
                resource.endLongitude()
        );
    }
}
