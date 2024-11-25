package pe.upc.trackmyroute.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.domain.services.UserCommandService;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.AuthenticatedUserResource;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.SignInResource;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.SignUpResource;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.UserResource;
import pe.upc.trackmyroute.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import pe.upc.trackmyroute.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import pe.upc.trackmyroute.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import pe.upc.trackmyroute.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.*;
import pe.upc.trackmyroute.profiles.domain.services.ProfileService;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoint")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final ProfileService profileService;

    public AuthenticationController(UserCommandService userCommandService, ProfileService profileService) {
        this.userCommandService = userCommandService;
        this.profileService = profileService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);

        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        createDefaultProfileForUser(user.get());

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    private void createDefaultProfileForUser(User user) {
        Profile defaultProfile = new Profile();
        defaultProfile.setUser(user);

        // Establecer valores predeterminados para el perfil
        defaultProfile.setName(new PersonName("Nombre Predeterminado", "Apellido Predeterminado")); // Cambia según sea necesario
        defaultProfile.setEmail(new EmailAddress("ejemplo@correo.com")); // Cambia según sea necesario
        defaultProfile.setPassword(new Password("Por implementar"));
        defaultProfile.setTransportCompany(new TransportCompany("Por implementar"));
        defaultProfile.setUserType(new UserType("Por implementar"));


        profileService.saveProfile(defaultProfile);
    }
}
