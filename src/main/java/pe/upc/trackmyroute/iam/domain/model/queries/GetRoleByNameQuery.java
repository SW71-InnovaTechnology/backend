package pe.upc.trackmyroute.iam.domain.model.queries;

import pe.upc.trackmyroute.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
