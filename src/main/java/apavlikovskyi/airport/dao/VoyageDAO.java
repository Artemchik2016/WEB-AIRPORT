package apavlikovskyi.airport.dao;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.*;

import apavlikovskyi.airport.entity.TicketEntity;
import apavlikovskyi.airport.entity.VoyageEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артем on 08.04.2017.
 */
public class VoyageDAO {

    public void save(VoyageEntity voyage) {
        try(PreparedStatement statement = getConnection().prepareStatement("INSERT INTO voyage VALUES (?,?,?,?)")) {
            statement.setInt(1,voyage.getId());
            statement.setString(2, voyage.getFlightNumber());
            statement.setString(3, voyage.getArrivalPort());
            statement.setString(4, voyage.getDeparturePort());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List getAll() {
        List<VoyageEntity> list = new ArrayList<>();
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM voyage")){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VoyageEntity voyageEntity = new VoyageEntity();
                voyageEntity.setFlightNumber(resultSet.getString("Flight_number"));
                voyageEntity.setArrivalPort(resultSet.getString("Arrival_port"));
                voyageEntity.setDeparturePort(resultSet.getString("Departure_port"));
                list.add(voyageEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public VoyageEntity getById (int id){
        VoyageEntity voyageEntity=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM voyage WHERE ID = ?")){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                voyageEntity = new VoyageEntity();
                voyageEntity.setId(resultSet.getInt("ID"));
                voyageEntity.setFlightNumber(resultSet.getString("Flight_number"));
                voyageEntity.setArrivalPort(resultSet.getString("Arrival_port"));
                voyageEntity.setDeparturePort(resultSet.getString("Departure_port"));
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
        return voyageEntity;
    }




    public void deleteById(int id) {
        try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM voyage WHERE ID =?")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void update(VoyageEntity voyageEntity) {
        try  (PreparedStatement statement = getConnection().prepareStatement("UPDATE voyage SET Flight_number=?,Arrival_port=?,Departure_port=? WHERE ID=?")){
            statement.setString(1,voyageEntity.getFlightNumber());
            statement.setString(2,voyageEntity.getArrivalPort());
            statement.setString(3,voyageEntity.getDeparturePort());
            statement.setInt(4,voyageEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
