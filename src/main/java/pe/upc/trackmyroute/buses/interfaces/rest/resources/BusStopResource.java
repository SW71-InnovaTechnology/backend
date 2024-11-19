package pe.upc.trackmyroute.buses.interfaces.rest.resources;

public record BusStopResource (
        Long id,
        String stopName,
        String latitude,
        String longitude
){
}
