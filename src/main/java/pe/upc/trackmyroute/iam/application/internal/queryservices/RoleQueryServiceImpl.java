package pe.upc.trackmyroute.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.iam.domain.model.entities.Role;
import pe.upc.trackmyroute.iam.domain.model.queries.GetAllRolesQuery;
import pe.upc.trackmyroute.iam.domain.model.queries.GetRoleByNameQuery;
import pe.upc.trackmyroute.iam.domain.services.RoleQueryService;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.roleName());
    }
}
