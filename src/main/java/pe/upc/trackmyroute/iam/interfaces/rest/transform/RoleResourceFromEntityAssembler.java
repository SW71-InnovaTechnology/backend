package pe.upc.trackmyroute.iam.interfaces.rest.transform;

import pe.upc.trackmyroute.iam.domain.model.entities.Role;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
