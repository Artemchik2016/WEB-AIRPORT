package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.AirplanesEntity;
import apavlikovskyi.airport.entity.ArrivalEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class ArrivalDAO {

    public  void deleteById(String s){
        try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM arrival WHERE Voyage_flightNumber = ?")){
            statement.setString(1,s);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ArrivalEntity> getAll(){
        List<ArrivalEntity> list=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM arrival")){
            resultSet=statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                ArrivalEntity arrivalEntity= new ArrivalEntity();
                arrivalEntity.setVoyage_id(resultSet.getString("Voyage_flightNumber"));
                arrivalEntity.setDate(resultSet.getString("Date"));
                arrivalEntity.setTime(resultSet.getString("Time"));
                arrivalEntity.setTerminal(resultSet.getString("Terminal"));
                arrivalEntity.setFlight_status(resultSet.getString("Flight_status"));
                arrivalEntity.setGate(resultSet.getString("Gate"));
                list.add(arrivalEntity);
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

    public ArrivalEntity getById (String s){
        ArrivalEntity arrivalEntity=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement= getConnection().prepareStatement("SELECT * FROM arrival WHERE Voyage_flightNumber = ?")) {
             statement.setString(1, s);
             resultSet=statement.executeQuery();
             while (resultSet.next()) {
                arrivalEntity = new ArrivalEntity();
                arrivalEntity.setVoyage_id(resultSet.getString("Voyage_flightNumber"));
                arrivalEntity.setDate(resultSet.getString("Date"));
                arrivalEntity.setTime(resultSet.getString("Time"));
                arrivalEntity.setTerminal(resultSet.getString("Terminal"));
                arrivalEntity.setFlight_status(resultSet.getString("Flight_status"));
                arrivalEntity.setGate(resultSet.getString("Gate"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrivalEntity;
    }



    public  void save(ArrivalEntity arrivalEntity){
        try(PreparedStatement statement= getConnection().prepareStatement("INSERT INTO arrival VALUES (?,?,?,?,?,?)")) {
            statement.setString(1,arrivalEntity.getVoyage_id());
            statement.setString(2,arrivalEntity.getDate());
            statement.setString(3,arrivalEntity.getTime());
            statement.setString(4,arrivalEntity.getTerminal());
            statement.setString(5,arrivalEntity.getFlight_status());
            statement.setString(6,arrivalEntity.getGate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void update(ArrivalEntity arrivalEntity){
        try (PreparedStatement statement= getConnection().prepareStatement("UPDATE arrival SET " +
                "Date = ?, Time = ?, Terminal = ?,Flight_status=?, Gate=? WHERE Voyage_flightNumber = ?")) {
            statement.setString(1,arrivalEntity.getVoyage_id());
            statement.setString(2,arrivalEntity.getDate());
            statement.setString(3,arrivalEntity.getTime());
            statement.setString(4,arrivalEntity.getTerminal());
            statement.setString(5,arrivalEntity.getFlight_status());
            statement.setString(6,arrivalEntity.getGate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



