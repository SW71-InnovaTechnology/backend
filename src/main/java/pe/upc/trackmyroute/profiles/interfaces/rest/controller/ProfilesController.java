package pe.upc.trackmyroute.profiles.interfaces.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.upc.trackmyroute.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.trackmyroute.profiles.domain.services.ProfileCommandService;
import pe.upc.trackmyroute.profiles.domain.services.ProfileQueryService;
import pe.upc.trackmyroute.profiles.domain.services.ProfileService;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.ProfileResource;
import pe.upc.trackmyroute.profiles.interfaces.rest.resources.UpdateProfileResource;
import pe.upc.trackmyroute.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import pe.upc.trackmyroute.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import pe.upc.trackmyroute.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;

/**
 * ProfilesController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/profiles")
@Tag(name="Profiles", description = "Profile Management Endpoints")
public class ProfilesController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;
    private final ProfileService profileService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService, ProfileService profileService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
        this.profileService = profileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(ProfileResourceFromEntityAssembler.transformResourceFromEntity(profile.get()));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfileById(
            @PathVariable Long profileId,
            @Validated @RequestBody UpdateProfileResource updateProfileResource) {

        // Convertir el recurso recibido en un comando
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.transform(updateProfileResource, profileId);

        // Llamar al servicio para actualizar el perfil
        var updatedProfile = profileCommandService.updateProfile(updateProfileCommand);

        if (updatedProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Devolver el perfil actualizado como respuesta
        return ResponseEntity.ok(ProfileResourceFromEntityAssembler.transformResourceFromEntity(updatedProfile.get()));
    }

}
