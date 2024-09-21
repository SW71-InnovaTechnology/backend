package pe.upc.trackmyroute.payment.domain.model.queries;

import pe.upc.trackmyroute.payment.domain.model.valueobjects.TicketAmount;

public record GetPaymentByTicketAmountQuery(TicketAmount ticket) {

}
