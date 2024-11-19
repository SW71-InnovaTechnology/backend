package pe.upc.trackmyroute.profiles.application.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.commands.UpdateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.EmailAddress;
import pe.upc.trackmyroute.profiles.domain.model.valueobjects.PersonName;
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

    @Override
    public Optional<Profile> updateProfile(UpdateProfileCommand command) {
        Optional<Profile> profileOpt = profileRepository.findById(command.getProfileId());

        if (profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            profile.setName(new PersonName(command.getFirstName(), command.getLastName()));
            profile.setEmail(new EmailAddress(command.getEmail()));

            // Otros campos opcionales para actualizar
            // profile.setPassword(new Password(command.getPassword()));
            // profile.setTransportCompany(new TransportCompany(command.getTransportCompany()));
            // profile.setUserType(new UserType(command.getUserType()));

            profileRepository.save(profile);
            return Optional.of(profile);
        }

        return Optional.empty();
    }


}
