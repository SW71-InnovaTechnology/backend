package pe.upc.trackmyroute.ways.interfaces.rest.transform;

import pe.upc.trackmyroute.ways.domain.model.aggregates.Whereabout;
import pe.upc.trackmyroute.ways.interfaces.rest.resources.WhereaboutResource;

public class WhereaboutResourceFromEntityAssembler {
    public static WhereaboutResource toResourceFromEntity(Whereabout entity){
        return new WhereaboutResource(entity.getId(), entity.getLatitude(), entity.getLongitude());
    }
}
