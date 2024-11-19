package pe.upc.trackmyroute.payment.interfaces.rest.resources;

public record PaymentResource(Long id, String bus, String origin, String destination, String TicketAmount, String TimeTrip ) {
}
