package pe.upc.trackmyroute.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.upc.trackmyroute.iam.application.internal.outboundservices.hashing.HashingService;
import pe.upc.trackmyroute.iam.application.internal.outboundservices.tokens.TokenService;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.domain.model.commands.DeleteUserCommand;
import pe.upc.trackmyroute.iam.domain.model.commands.SignInCommand;
import pe.upc.trackmyroute.iam.domain.model.commands.SignUpCommand;
import pe.upc.trackmyroute.iam.domain.model.entities.Role;
import pe.upc.trackmyroute.iam.domain.model.valueobjects.Roles;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private HashingService hashingService;
    @Mock
    private TokenService tokenService;
    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    private User user;
    private List<Role> roles;
    private Roles rol;

    @BeforeEach
    void setUp() {
        rol = Roles.ROLE_USER;
        roles = new ArrayList<>();
        roles.add(new Role(rol));
        user = new User(
                "admin",
                "admin123",
                roles
        );
    }

    @Test
    void signUp() {
        //Arrange
        SignUpCommand command = new SignUpCommand("admin", "admin123", List.of("ROLE_USER"));
        when(userRepository.existsByUsername(command.username())).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_USER)).thenReturn(Optional.of(new Role(Roles.ROLE_USER)));
        when(hashingService.encode(command.password())).thenReturn("hashedPassword");

        User newUser = new User(command.username(), "hashedPassword", roles);
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.of(newUser));

        //Act
        Optional<User> result = userCommandService.handle(command);

        //Assert
        assertTrue(result.isPresent());
        assertEquals("admin", result.get().getUsername());
        verify(userRepository).save(any(User.class));
        verify(roleRepository).findByName(Roles.ROLE_USER);
        verify(hashingService).encode(command.password());
    }

    @Test
    void signIn() {
        //Arrange
        SignInCommand command = new SignInCommand("admin", "admin123");
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.of(user));
        when(hashingService.matches(command.password(), user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(command.username())).thenReturn("generatedToken");

        //Act
        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

        //Assert
        assertTrue(result.isPresent());
        assertEquals("admin", result.get().getLeft().getUsername());
        assertEquals("generatedToken", result.get().getRight());
        verify(userRepository).findByUsername(command.username());
        verify(hashingService).matches(command.password(), user.getPassword());
        verify(tokenService).generateToken(command.username());
    }

    @Test
    void deleteUser() {
        //Arrange
        DeleteUserCommand command = new DeleteUserCommand("admin");
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.of(user));

        //Act
        Optional<User> result = userCommandService.handle(command);

        //Assert
        assertTrue(result.isPresent());
        verify(userRepository).findByUsername(command.username());
        verify(userRepository).save(user);
        verify(userRepository).delete(user);
    }
}