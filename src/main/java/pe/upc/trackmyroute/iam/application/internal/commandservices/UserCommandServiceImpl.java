package pe.upc.trackmyroute.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.iam.application.internal.outboundservices.hashing.HashingService;
import pe.upc.trackmyroute.iam.application.internal.outboundservices.tokens.TokenService;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.domain.model.commands.DeleteUserCommand;
import pe.upc.trackmyroute.iam.domain.model.commands.SignInCommand;
import pe.upc.trackmyroute.iam.domain.model.commands.SignUpCommand;
import pe.upc.trackmyroute.iam.domain.model.entities.Role;
import pe.upc.trackmyroute.iam.domain.model.valueobjects.Roles;
import pe.upc.trackmyroute.iam.domain.services.UserCommandService;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var stringRoles = command.roles();
        var roles = new ArrayList<Role>();
        if (stringRoles == null || stringRoles.isEmpty()) {
            var storedRole = roleRepository.findByName(Roles.ROLE_USER);
            storedRole.ifPresent(roles::add);
        } else {
            stringRoles.forEach(role -> {
                var storedRole = roleRepository.findByName(Roles.valueOf(role));
                storedRole.ifPresent(roles::add);
            });
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty())
            throw new RuntimeException("Username not found");

        if(!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Wrong password");

        var token = tokenService.generateToken(user.get().getUsername());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(DeleteUserCommand command) {
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty()) throw new RuntimeException("Username not found");

        user.get().getRoles().clear();
        userRepository.save(user.get());

        userRepository.delete(user.get());
        return user;
    }
}
