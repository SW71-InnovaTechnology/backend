package pe.upc.trackmyroute.payment.domain.model.valueobjects;

import jakarta.validation.constraints.NotBlank;

public record Origin(@NotBlank String originStop, @NotBlank String originLatitude, @NotBlank String originLongitude) {
    public Origin(){this(null, null, null);}
    public Origin{
        if(originStop == null || originStop.isBlank()){
            throw new IllegalArgumentException("originStop cannot be null");
        }
        if(originLatitude == null || originLatitude.isBlank()){
            throw new IllegalArgumentException("latitude cannot be null");
        }

        if(originLongitude == null || originLongitude.isBlank()){
            throw new IllegalArgumentException("longitude cannot be null");
        }
    }

    public String getFullOriginCoordinate(){
        return String.format("%s,%s", originLatitude, originLongitude);
    }
}
