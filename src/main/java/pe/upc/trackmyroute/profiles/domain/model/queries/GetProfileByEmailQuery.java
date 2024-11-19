package pe.upc.trackmyroute.profiles.domain.model.queries;

import pe.upc.trackmyroute.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
