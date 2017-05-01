package apavlikovskyi.airport.entity;

/**
 * Created by Артем on 08.04.2017.
 */
public class VoyageEntity {
    private int id;
    private String flightNumber;
    private String arrivalPort;
    private String departurePort;


    public VoyageEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VoyageEntity(int id, String flightNumber, String arrivalPort, String departurePort) {
        this.id=id;
        this.flightNumber = flightNumber;
        this.arrivalPort = arrivalPort;
        this.departurePort = departurePort;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoyageEntity)) return false;

        VoyageEntity that = (VoyageEntity) o;

        if (id != that.id) return false;
        if (flightNumber != null ? !flightNumber.equals(that.flightNumber) : that.flightNumber != null) return false;
        if (arrivalPort != null ? !arrivalPort.equals(that.arrivalPort) : that.arrivalPort != null) return false;
        return departurePort != null ? departurePort.equals(that.departurePort) : that.departurePort == null;
    }
}
