package pe.upc.trackmyroute.iam.domain.services;

import pe.upc.trackmyroute.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
