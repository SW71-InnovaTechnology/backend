package pe.upc.trackmyroute.profiles.interfaces.rest.transform;

import pe.upc.trackmyroute.profiles.domain.model.commands.UpdateProfileCommand;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand transform(UpdateProfileResource resource, Long profileId) {
        return new UpdateProfileCommand(profileId, resource.getFirstName(), resource.getLastName(), resource.getEmail());
    }
}