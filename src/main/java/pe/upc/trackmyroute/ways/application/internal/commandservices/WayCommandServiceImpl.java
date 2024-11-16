package pe.upc.trackmyroute.ways.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.domain.model.commands.CreateWayCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.DeleteWayCommand;
import pe.upc.trackmyroute.ways.domain.model.commands.UpdateWayCommand;
import pe.upc.trackmyroute.ways.domain.services.WayCommandService;
import pe.upc.trackmyroute.ways.infrastructure.persistence.jpa.repositories.WayRepository;

import java.util.Optional;

@Service
public class WayCommandServiceImpl implements WayCommandService {

    private final WayRepository wayRepository;

    public WayCommandServiceImpl(WayRepository wayRepository) {
        this.wayRepository = wayRepository;
    }


    @Override
    public Long handle(CreateWayCommand command) {
        if(wayRepository.existsByStartLatitudeAndStartLongitude(command.startLatitude(), command.startLongitude())){
            throw new IllegalArgumentException("The start latitude and longitude already exists");
        }
        if(wayRepository.existsByEndLatitudeAndEndLongitude(command.endLatitude(), command.endLongitude())){
            throw new IllegalArgumentException("The end latitude and longitude already exists");
        }
        var way = new Way(command);
        try{
            wayRepository.save(way);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error saving the way");
        }
        return way.getId();
    }

    @Override
    public Optional<Way> handle(UpdateWayCommand command) {
        if(wayRepository.existsByStartLatitudeAndStartLongitude(command.startLatitude(), command.startLongitude())){
            throw new IllegalArgumentException("The start latitude and longitude already exists");
        }
        var result = wayRepository.findById(command.id());
        if(result.isEmpty()){
            throw new IllegalArgumentException("The way does not exist");
        }
        var wayToUpdate = result.get();
        try{
            var updatedWay = wayRepository.save(wayToUpdate.updateInformation(command.startLatitude(), command.startLongitude(), command.endLatitude(), command.endLongitude()));
            return Optional.of(updatedWay);
        }catch (Exception e){
            throw new IllegalArgumentException("Error updating the way");
        }
    }

    @Override
    public void handle(DeleteWayCommand command) {
        if(!wayRepository.existsById(command.wayId())){
            throw new IllegalArgumentException("The way does not exist");
        }
        try{
            wayRepository.deleteById(command.wayId());
        }catch (Exception e){
            throw new IllegalArgumentException("Error deleting the way");
        }
    }
}
