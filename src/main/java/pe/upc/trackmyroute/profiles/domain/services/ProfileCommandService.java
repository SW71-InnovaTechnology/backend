package pe.upc.trackmyroute.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

@Service
public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);

    Optional<Profile> updateProfile(UpdateProfileCommand command);
}
