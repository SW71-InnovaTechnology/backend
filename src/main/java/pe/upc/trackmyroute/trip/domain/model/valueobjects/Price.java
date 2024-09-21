package pe.upc.trackmyroute.trip.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Embeddable
public record Price(
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal amount,
        @NotBlank String currency
) {
    public Price() {
        this(null, null);
    }

    public Price {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or blank");
        }
    }

    public String getPrice() {
        return String.format("%s %s", amount, currency);
    }
}