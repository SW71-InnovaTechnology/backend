package pe.upc.trackmyroute.ways.domain.services;

import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWayCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWayCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWayCommand;

import java.util.Optional;

public interface WayCommandService {
    Long handle(CreateWayCommand command);
    Optional<Way> handle(UpdateWayCommand command);
    void handle(DeleteWayCommand command);
}
