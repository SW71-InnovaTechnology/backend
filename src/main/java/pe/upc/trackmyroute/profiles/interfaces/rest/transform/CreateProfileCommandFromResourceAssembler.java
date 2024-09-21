package pe.upc.trackmyroute.profiles.interfaces.rest.transform;

import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.userType(),
                resource.password(),
                resource.transportCompany()
        );
    }
}
