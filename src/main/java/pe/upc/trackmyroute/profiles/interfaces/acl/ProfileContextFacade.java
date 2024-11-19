package pe.upc.trackmyroute.profiles.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.EmailAddress;
import pe.upc.trackmyroute.profiles.domain.services.ProfileCommandService;
import pe.upc.trackmyroute.profiles.domain.services.ProfileQueryService;

@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String firstName,
                              String lastName,
                              String email,
                              String userType,
                              String password,
                              String transportCompany) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, userType, password, transportCompany);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }
}
