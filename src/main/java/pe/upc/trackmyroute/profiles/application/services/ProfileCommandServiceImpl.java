package pe.upc.trackmyroute.profiles.application.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.EmailAddress;
import pe.upc.trackmyroute.profiles.domain.services.ProfileCommandService;
import pe.upc.trackmyroute.profiles.infrastructure.persistance.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + emailAddress + " already exists");
        });

        var profile = new Profile(command);
        profileRepository.save(profile);

        return Optional.of(profile);
    }
}
