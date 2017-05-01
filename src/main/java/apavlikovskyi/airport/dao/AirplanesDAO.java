package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.AirplanesEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class AirplanesDAO {

        public  void deleteById(String s){
            try(PreparedStatement statement=getConnection().prepareStatement("DELETE FROM airplanes WHERE Voyage_flightNumber = ?")) {
                statement.setString(1,s);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<AirplanesEntity> getAll(){
            List<AirplanesEntity> list=null;
            ResultSet resultSet=null;
            try(PreparedStatement statement=getConnection().prepareStatement("SELECT * FROM airplanes")) {
                resultSet=statement.executeQuery();
                list = new ArrayList<>();
                while (resultSet.next()) {
                    AirplanesEntity airplanesEntity= new AirplanesEntity();
                    airplanesEntity.setVoyage_flightNumber(resultSet.getString("Voyage_flightNumber"));
                    airplanesEntity.setName(resultSet.getString("Name"));
                    airplanesEntity.setSeats_capacity(resultSet.getInt("Seats_capacity"));
                    list.add(airplanesEntity);
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

        public AirplanesEntity getById (String s){
            AirplanesEntity airplanesEntity=null;
            ResultSet resultSet=null;
            try(PreparedStatement statement=getConnection().prepareStatement("SELECT * FROM airplanes WHERE Voyage_flightNumber = ?")) {
                statement.setString(1, s);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    airplanesEntity = new AirplanesEntity();
                    airplanesEntity.setVoyage_flightNumber(resultSet.getString("Voyage_flightNumber"));
                    airplanesEntity.setName(resultSet.getString("Name"));
                    airplanesEntity.setSeats_capacity(resultSet.getInt("Seats_capacity"));
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
            return airplanesEntity;
        }

        public  void save(AirplanesEntity airplanesEntity){
            try(PreparedStatement statement=getConnection().prepareStatement("INSERT INTO airplanes VALUES (?,?,?)")) {
                statement.setString(1,airplanesEntity.getVoyage_flightNumber());
                statement.setString(2,airplanesEntity.getName());
                statement.setInt(3,airplanesEntity.getSeats_capacity());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public  void update(AirplanesEntity airplanesEntity){
            try(PreparedStatement statement=getConnection().prepareStatement("UPDATE airplanes SET " +
                    "Name = ?, Seats_capacity = ? WHERE Voyage_flightNumber = ?")) {
                statement.setString(1,airplanesEntity.getName());
                statement.setInt(2,airplanesEntity.getSeats_capacity());
                statement.setString(3,airplanesEntity.getVoyage_flightNumber());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

