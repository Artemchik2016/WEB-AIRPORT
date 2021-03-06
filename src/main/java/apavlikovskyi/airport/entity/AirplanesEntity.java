package apavlikovskyi.airport.entity;

/**
 * Created by Diana P on 04.04.2017.
 */
public class AirplanesEntity {
    private int id;
    private String Voyage_flightNumber;
    private  String name;
    private int seats_capacity;
    public AirplanesEntity(){}


    public AirplanesEntity(String voyage_id, String name, int seats_capacity) {
        this.Voyage_flightNumber = voyage_id;
        this.name = name;
        this.seats_capacity = seats_capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return Voyage_flightNumber;
    }

    public void setVoyage_flightNumber(String voyage_flightNumber) {
        this.Voyage_flightNumber = voyage_flightNumber;
    }

    public String getModel() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats_capacity() {
        return seats_capacity;
    }

    public void setSeats_capacity(int seats_capacity) {
        this.seats_capacity = seats_capacity;
    }
}
