package pe.upc.trackmyroute.payment.interfaces.rest.transform;

import pe.upc.trackmyroute.payment.domain.model.aggregates.Payment;
import pe.upc.trackmyroute.payment.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource transformResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getFullBusName(),
                entity.getFullOriginCoordinate(),
                entity.getFullDestinationCoordinate(),
                entity.getFullTicketAmount(),
                entity.getFullTimeTrip());
    }

}
