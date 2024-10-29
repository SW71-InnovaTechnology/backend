package pe.upc.trackmyroute.profiles.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResource {
    private String firstName;
    private String lastName;
    private String email;

    // private String password;
    // private String transportCompany;
    // private String userType;

}