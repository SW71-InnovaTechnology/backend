package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.aggregates.Way;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.WayResource;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.WhereaboutResource;

import java.util.List;
import java.util.stream.Collectors;

public class WayResourceFromEntityAssembler {
    public static WayResource toResourceFromEntity(Way entity){
        List<WhereaboutResource> whereabouts =entity.getWhereabout().stream()
                .map(WhereaboutResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new WayResource(entity.getId(),entity.getStartLatitude(),entity.getStartLongitude(),entity.getEndLatitude(),entity.getEndLongitude(),whereabouts);
    }
}
