package pe.upc.trackmyroute.payment.application.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.payment.domain.model.aggregates.Payment;
import pe.upc.trackmyroute.payment.domain.model.commands.CreatePaymentCommand;
import pe.upc.trackmyroute.payment.domain.model.valueobjects.*;
import pe.upc.trackmyroute.payment.domain.services.PaymentCommandService;
import pe.upc.trackmyroute.payment.infraestructure.persistence.jpa.repositories.PaymentRepository;

import java.util.Optional;
@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {

        var busName = command.busName();

        paymentRepository.findByBus_BusName(busName).map(payment -> {
            throw new IllegalArgumentException("Bus " + busName + " already exists");
        });


        var payment = Payment.builder()
                .bus(new Bus(command.busName()))
                .destination(new Destination(command.destinationStop(), command.destinationLatitude(), command.destinationLongitude()))
                .origin(new Origin(command.originStop(), command.originLatitude(), command.originLongitude()))
                .ticket(new TicketAmount(command.exchange(), command.price()))
                .timeTrip(new TimeTrip(command.hour(), command.minutes(), command.dayTime()))
                .build();

        paymentRepository.save(payment);

        return Optional.of(payment);
    }
}
