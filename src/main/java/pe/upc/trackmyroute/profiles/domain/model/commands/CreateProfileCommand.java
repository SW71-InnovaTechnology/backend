package pe.upc.trackmyroute.profiles.domain.model.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateProfileCommand(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        String type,
        String password,
        String transportCompany
) {


}
