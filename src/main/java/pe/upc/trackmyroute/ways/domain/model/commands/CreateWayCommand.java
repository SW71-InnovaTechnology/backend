package pe.upc.trackmyroute.ways.domain.model.commands;

public record CreateWayCommand(String startLatitude, String startLongitude, String endLatitude, String endLongitude){}
