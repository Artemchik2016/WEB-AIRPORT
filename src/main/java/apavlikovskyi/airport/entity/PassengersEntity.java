package apavlikovskyi.airport.entity;

/**
 * Created by Diana P on 04.04.2017.
 */
public class PassengersEntity {
    private int id;
    private String first_name;
    private String last_name;
    private String nationality;
    private String passport;
    private String dob;
    private String sex;

    public PassengersEntity() {}

    public PassengersEntity(int id, String first_name, String last_name, String nationality, String passport, String dob, String sex) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nationality = nationality;
        this.passport = passport;
        this.dob = dob;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
