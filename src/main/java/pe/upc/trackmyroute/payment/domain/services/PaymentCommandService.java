package pe.upc.trackmyroute.payment.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.payment.domain.model.aggregates.Payment;
import pe.upc.trackmyroute.payment.domain.model.commands.CreatePaymentCommand;

import java.util.Optional;

@Service
public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
}
