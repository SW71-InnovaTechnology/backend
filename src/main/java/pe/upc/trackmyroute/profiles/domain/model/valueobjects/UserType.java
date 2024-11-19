package pe.upc.trackmyroute.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserType(String type) {
    public UserType() {this(null);}

    public String getType() {return type;}
}
