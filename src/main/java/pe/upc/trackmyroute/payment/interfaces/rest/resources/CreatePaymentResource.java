package pe.upc.trackmyroute.payment.interfaces.rest.resources;

public record CreatePaymentResource(
        String busName,
        String originStop,
        String originLatitude,
        String originLongitude,
        String destinationStop,
        String destinationLatitude,
        String destinationLongitude,
        String exchange,
        String price,
        String hour,
        String minutes,
        String dayTime
) {

}
