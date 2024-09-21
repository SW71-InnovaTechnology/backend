package pe.upc.trackmyroute.payment.domain.model.valueobjects;

import jakarta.validation.constraints.NotBlank;

public record TimeTrip(@NotBlank String hour, @NotBlank String minutes, @NotBlank String dayTime) {
    public TimeTrip(){this(null, null, null);}

    public TimeTrip{
        if(hour == null || hour.isBlank()){
            throw new IllegalArgumentException("hour cannot be null or blank");
        }

        if(minutes == null || minutes.isBlank()){
            throw new IllegalArgumentException("minutes cannot be null or blank");
        }

        if(dayTime == null || dayTime.isBlank()){
            throw new IllegalArgumentException("dayTime cannot be null or blank");
        }
    }

    public String getTimeTrip(){
        return String.format("%s%s%s", hour, minutes, dayTime );
    }

}
