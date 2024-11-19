package pe.upc.trackmyroute.ways.domain.model.commands;

public record UpdateWayCommand(Long id,String startLatitude, String startLongitude,String endLatitude, String endLongitude) { }
