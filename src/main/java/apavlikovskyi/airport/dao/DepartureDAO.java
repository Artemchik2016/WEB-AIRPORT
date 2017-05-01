package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.DepartureEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class DepartureDAO {

    public  void deleteById(String s){
        try(PreparedStatement statement = getConnection().prepareStatement("DELETE FROM departure WHERE Voyage_flightNumber = ?")) {
            statement.setString(1,s);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DepartureEntity> getAll(){
        List<DepartureEntity> list=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM departure")){
            resultSet=statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                DepartureEntity departureEntity= new DepartureEntity();
                departureEntity.setVoyage_id(resultSet.getString("Voyage_flightNumber"));
                departureEntity.setDate(resultSet.getString("Date"));
                departureEntity.setTime(resultSet.getString("Time"));
                departureEntity.setTerminal(resultSet.getString("Terminal"));
                departureEntity.setFlight_status(resultSet.getString("Flight_status"));
                departureEntity.setGate(resultSet.getString("Gate"));
                list.add(departureEntity);
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

    public DepartureEntity getById (String s){
        DepartureEntity departureEntity=null;
        ResultSet resultSet=null;
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM departure WHERE Voyage_flightNumber = ?")) {
            statement.setString(1,s);
            resultSet= statement.executeQuery();
            while (resultSet.next()) {
                departureEntity = new DepartureEntity();
                departureEntity.setVoyage_id(resultSet.getString("Voyage_flightNumber"));
                departureEntity.setDate(resultSet.getString("Date"));
                departureEntity.setTime(resultSet.getString("Time"));
                departureEntity.setTerminal(resultSet.getString("Terminal"));
                departureEntity.setFlight_status(resultSet.getString("Flight_status"));
                departureEntity.setGate(resultSet.getString("Gate"));
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
        return departureEntity;
    }



    public  void save(DepartureEntity departureEntity){
        try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO departure VALUES (?,?,?,?,?,?)")) {
            statement.setString(1,departureEntity.getVoyage_id());
            statement.setString(2,departureEntity.getDate());
            statement.setString(3,departureEntity.getTime());
            statement.setString(4,departureEntity.getTerminal());
            statement.setString(5,departureEntity.getFlight_status());
            statement.setString(6,departureEntity.getGate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void update(DepartureEntity departureEntity){
        try  (PreparedStatement statement = getConnection().prepareStatement("UPDATE departure SET " +
                "Date = ?, Time = ?, Terminal = ?,Flight_status=?, Gate=? WHERE Voyage_flightNumber=?")){
            statement.setString(1,departureEntity.getVoyage_id());
            statement.setString(2,departureEntity.getDate());
            statement.setString(3,departureEntity.getTime());
            statement.setString(4,departureEntity.getTerminal());
            statement.setString(5,departureEntity.getFlight_status());
            statement.setString(6,departureEntity.getGate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

