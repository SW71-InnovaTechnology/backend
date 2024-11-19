package pe.upc.trackmyroute.payment.domain.model.valueobjects;

import jakarta.validation.constraints.NotBlank;

public record TicketAmount(@NotBlank String exchange, @NotBlank String price) {
    public TicketAmount(){this(null, null);}
    public TicketAmount{
        if(exchange == null || exchange.isBlank()){
            throw new IllegalArgumentException("exchange cannot be null or blank");
        }

        if(price == null || price.isBlank()){
            throw new IllegalArgumentException("price cannot be null or blank");
        }
    }

    public String getFullTicketAmount(){
        return String.format("%s %s", exchange, price);
    }
}
