package pe.upc.trackmyroute.ways.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWhereaboutsCommand;
import pe.upc.trackmyroute.ways.domain.services.WhereaboutCommandService;
import pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories.WayRepository;
import pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories.WhereaboutRepository;

import java.util.Optional;

@Service
public class WhereaboutCommandServiceImpl implements WhereaboutCommandService {
    private final WayRepository wayRepository;
    private final WhereaboutRepository whereaboutRepository;

    public WhereaboutCommandServiceImpl(WayRepository wayRepository, WhereaboutRepository whereaboutRepository) {
        this.wayRepository = wayRepository;
        this.whereaboutRepository = whereaboutRepository;
    }

    @Transactional
    @Override
    public Long handle(CreateWhereaboutsCommand command) {
        Way way = wayRepository.findById(command.getWayId())
                .orElseThrow(() -> new IllegalArgumentException("Way not found"));

        Whereabout whereabout = new Whereabout(command, way);
        try{
            whereaboutRepository.save(whereabout);
        }catch (Exception e){
            throw new IllegalArgumentException("Error saving whereabout");
        }
        return whereabout.getId();
    }

    @Override
    public Optional<Whereabout> handle(UpdateWhereaboutsCommand command) {
        if(whereaboutRepository.existsByLatitudeAndLongitude(command.latitude(), command.longitude()))
            throw new IllegalArgumentException("Whereabout already exists");
        var result = whereaboutRepository.findById(command.id());
        if(result.isEmpty()) throw new IllegalArgumentException("Whereabout not found");
        var whereaboutToUpdate = result.get();
        try{
            var updatedWhereabout = whereaboutRepository.save(whereaboutToUpdate.updateInformation(command.latitude(), command.longitude()));
            return Optional.of(updatedWhereabout);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error updating whereabout" + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteWhereaboutsCommand command) {
        if(!whereaboutRepository.existsById(command.whereaboutsId())) {
            throw new IllegalArgumentException("Whereabout does not exist");
        }
        try {
            whereaboutRepository.deleteById(command.whereaboutsId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting whereabout");
        }
    }
}
