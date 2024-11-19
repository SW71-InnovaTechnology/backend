package pe.upc.trackmyroute.profiles.interfaces.rest.transform;

import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource transformResourceFromEntity(Profile entity){
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmail(),
                entity.getUserType(), entity.getPassword(), entity.getTransportCompany());
    }
}
