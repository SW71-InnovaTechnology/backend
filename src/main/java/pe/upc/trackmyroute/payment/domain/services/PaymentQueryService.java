package pe.upc.trackmyroute.payment.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.payment.domain.model.aggregates.Payment;
import pe.upc.trackmyroute.payment.domain.model.queries.GetAllPaymentsQuery;
import pe.upc.trackmyroute.payment.domain.model.queries.GetPaymentByBusNameQuery;
import pe.upc.trackmyroute.payment.domain.model.queries.GetPaymentByIdQuery;
import pe.upc.trackmyroute.payment.domain.model.queries.GetPaymentByTicketAmountQuery;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    Optional<Payment> handle(GetPaymentByTicketAmountQuery query);
    Optional<Payment> handle(GetPaymentByBusNameQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
}
