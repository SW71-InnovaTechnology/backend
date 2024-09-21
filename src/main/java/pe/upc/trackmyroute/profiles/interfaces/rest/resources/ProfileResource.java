package pe.upc.trackmyroute.profiles.interfaces.rest.resources;

public record ProfileResource(Long id, String fullName, String email, String userType, String password, String transportCompany) {
}
