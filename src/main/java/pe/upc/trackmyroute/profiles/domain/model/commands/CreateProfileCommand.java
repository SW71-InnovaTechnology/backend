package pe.upc.trackmyroute.profiles.domain.model.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateProfileCommand(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email,
        @NotBlank String type,
        @NotBlank String password,
        String transportCompany
) {


}
