package pe.upc.trackmyroute.ways.domain.model.commands;

public class CreateWhereaboutsCommand{
    private Long wayId;
    private String latitude;
    private String longitude;

    public CreateWhereaboutsCommand(Long wayId, String latitude, String longitude){
        this.wayId = wayId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Long getWayId(){
        return wayId;
    }
    public String getLatitude(){
        return latitude;
    }
    public String getLongitude(){
        return longitude;
    }
}
