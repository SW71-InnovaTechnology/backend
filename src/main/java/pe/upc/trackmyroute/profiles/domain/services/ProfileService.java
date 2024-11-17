package pe.upc.trackmyroute.profiles.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.trackmyroute.profiles.infrastructure.persistance.jpa.repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Profile saveProfile(Profile profile) {
        // No es necesario buscar el usuario nuevamente, ya que ya lo tenemos en el perfil
        return profileRepository.save(profile);
    }
}