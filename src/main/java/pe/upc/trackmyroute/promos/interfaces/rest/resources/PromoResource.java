package pe.upc.trackmyroute.promos.interfaces.rest.resources;

import java.util.Date;

public record PromoResource(Long id,
                            String name,
                            String description,
                            String imageUrl,
                            Date validUntil) {
}
