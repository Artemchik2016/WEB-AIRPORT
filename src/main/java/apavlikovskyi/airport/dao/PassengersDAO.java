package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.PassengersEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class PassengersDAO {

    public  void deleteById(int id){
        try(PreparedStatement statement = getConnection().prepareStatement("DELETE FROM passengers WHERE ID = ?")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PassengersEntity> getAll(){
        List<PassengersEntity> list=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM passengers")){
            resultSet=statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                PassengersEntity passengersEntity= new PassengersEntity();
                passengersEntity.setId(resultSet.getInt("ID"));
                passengersEntity.setFirst_name(resultSet.getString("First_name"));
                passengersEntity.setLast_name(resultSet.getString("Last_name"));
                passengersEntity.setNationality(resultSet.getString("Nationality"));
                passengersEntity.setNationality(resultSet.getString("Passport"));
                passengersEntity.setPassport(resultSet.getString("DOB"));
                passengersEntity.setDob(resultSet.getString("SEX"));
                list.add(passengersEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public PassengersEntity getById (int id){
        PassengersEntity passengersEntity=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM passengers WHERE ID = ?")) {
            statement.setInt(1,id);
            resultSet= statement.executeQuery();
            while(resultSet.next()) {
                passengersEntity = new PassengersEntity();
                passengersEntity.setId(resultSet.getInt("ID"));
                passengersEntity.setFirst_name(resultSet.getString("First_name"));
                passengersEntity.setLast_name(resultSet.getString("Last_name"));
                passengersEntity.setNationality(resultSet.getString("Nationality"));
                passengersEntity.setPassport(resultSet.getString("Passport"));
                passengersEntity.setDob(resultSet.getString("DOB"));
                passengersEntity.setSex(resultSet.getString("SEX"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passengersEntity;
    }



    public  void save(PassengersEntity passengersEntity){
        try(PreparedStatement statement = getConnection().prepareStatement("INSERT INTO passengers VALUES (?,?,?,?,?,?,?)")) {
            statement.setInt(1,passengersEntity.getId());
            statement.setString(2,passengersEntity.getFirst_name());
            statement.setString(3,passengersEntity.getLast_name());
            statement.setString(4,passengersEntity.getNationality());
            statement.setString(5,passengersEntity.getPassport());
            statement.setString(6,passengersEntity.getDob());
            statement.setString(7,passengersEntity.getSex());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void update(PassengersEntity passengersEntity){
        try (PreparedStatement statement = getConnection().prepareStatement("UPDATE passengers SET " +
                "First_name = ?, Last_name = ?, Nationality = ?,Passport=?, DOB=?, SEX=? WHERE ID = ?")){
            statement.setString(1,passengersEntity.getFirst_name());
            statement.setString(2,passengersEntity.getLast_name());
            statement.setString(3,passengersEntity.getNationality());
            statement.setString(4,passengersEntity.getPassport());
            statement.setString(5,passengersEntity.getDob());
            statement.setString(6,passengersEntity.getSex());
            statement.setInt(7,passengersEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

