package apavlikovskyi.airport.entity;

/**
 * Created by Diana P on 04.04.2017.
 */
public class TicketEntity {
    private int id;
    private String voyageId;
    private String seatClass;
    private int passengerId;
    private String seatNumber;


    public  TicketEntity(){}

    public TicketEntity(int id,String voyageId, String seatClass, int passengerId, String seatNumber) {
        this.id=id;
        this.voyageId = voyageId;
        this.seatClass = seatClass;
        this.passengerId = passengerId;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity)) return false;

        TicketEntity that = (TicketEntity) o;

        if (id != that.id) return false;
        if (passengerId != that.passengerId) return false;
        if (voyageId != null ? !voyageId.equals(that.voyageId) : that.voyageId != null) return false;
        if (seatClass != null ? !seatClass.equals(that.seatClass) : that.seatClass != null) return false;
        return seatNumber != null ? seatNumber.equals(that.seatNumber) : that.seatNumber == null;
    }

}
