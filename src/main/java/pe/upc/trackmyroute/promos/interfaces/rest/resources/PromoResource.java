package pe.upc.trackmyroute.promos.interfaces.rest.resources;

import java.util.Date;

public record PromoResource(Long id,
                            String Name,
                            String Description,
                            String ImageUrl,
                            Date ValidUntil) {
}
