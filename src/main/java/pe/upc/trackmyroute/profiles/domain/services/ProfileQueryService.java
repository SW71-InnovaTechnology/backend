package pe.upc.trackmyroute.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.profiles.domain.model.aggregates.Profile;
import pe.upc.trackmyroute.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.upc.trackmyroute.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

@Service
public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
