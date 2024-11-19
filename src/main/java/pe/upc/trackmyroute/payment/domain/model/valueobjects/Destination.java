package pe.upc.trackmyroute.payment.domain.model.valueobjects;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Destination(@NotBlank String destinationStop, @NotBlank String destinationLatitude, @NotBlank String destinationLongitude) {
    public Destination() { this(null, null, null);}

    public Destination {
        if(destinationStop == null || destinationStop.isBlank()){
            throw new IllegalArgumentException("destination cannot be null");
        }

        if(destinationLatitude == null || destinationLatitude.isBlank()){
            throw new IllegalArgumentException("latitude cannot be null");
        }

        if(destinationLongitude == null || destinationLongitude.isBlank()){
            throw new IllegalArgumentException("latitude cannot be null");
        }

    }

    public String getFullDestinationCoordinate(){
        return String.format("%s,%s", destinationLatitude, destinationLongitude);
    }
}
