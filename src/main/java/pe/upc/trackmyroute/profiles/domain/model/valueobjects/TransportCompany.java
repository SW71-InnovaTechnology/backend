package pe.upc.trackmyroute.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TransportCompany(String transportCompany) {
    public TransportCompany() {this(null);}

    public String getTransportCompany() {return transportCompany;}
}
