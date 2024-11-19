package pe.upc.trackmyroute.profiles.domain.model.commands;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProfileCommand {

    private final Long profileId;
    private final String firstName;
    private final String lastName;
    private final String email;

    // Otros campos opcionales como password, etc.

    public UpdateProfileCommand(Long profileId, String firstName, String lastName, String email) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters para otros campos si es necesario
}