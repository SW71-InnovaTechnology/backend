package pe.upc.trackmyroute.payment.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.trackmyroute.payment.domain.model.aggregates.Payment;
import pe.upc.trackmyroute.payment.domain.model.valueobjects.TicketAmount;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTicket(TicketAmount ticket);
    Optional<Payment> findByBus_BusName(String busName);
}
