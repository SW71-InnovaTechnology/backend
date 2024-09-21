package pe.upc.trackmyroute.iam.interfaces.rest.transform;

import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}

