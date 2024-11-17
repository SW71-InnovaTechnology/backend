package pe.upc.trackmyroute.ways.domain.services;

import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWhereaboutsCommand;


import java.util.Optional;

public interface WhereaboutCommandService {
    Long handle(CreateWhereaboutsCommand command);
    Optional<Whereabout> handle(UpdateWhereaboutsCommand command);
    void handle(DeleteWhereaboutsCommand command);
}
