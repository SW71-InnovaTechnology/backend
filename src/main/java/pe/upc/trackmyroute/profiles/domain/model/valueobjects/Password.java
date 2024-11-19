package pe.upc.trackmyroute.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {
    public Password() {this(null);}

    public String getPassword() {return this.password;}
}
