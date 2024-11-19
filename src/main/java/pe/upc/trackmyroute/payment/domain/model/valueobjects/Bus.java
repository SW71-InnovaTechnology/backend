package pe.upc.trackmyroute.payment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Bus(@NotBlank String busName) {
    public Bus() { this(null);}

    public Bus {
        if(busName == null || busName.isBlank()){
            throw new IllegalArgumentException("destination cannot be null");
        }
    }

    public String getFullBusName(){
        return String.format("%s", busName);
    }
}
