package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.TicketEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.*;
/**
 * Created by Diana P on 04.04.2017.
 */
public class TicketDAO {

    public  void deleteById(int id){
        try(PreparedStatement statement = getConnection().prepareStatement("DELETE FROM ticket WHERE ID = ?")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TicketEntity> getAll(){
        List<TicketEntity> list=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM ticket")){
            resultSet=statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                TicketEntity ticket= new TicketEntity();
                ticket.setId(resultSet.getInt("ID"));
                ticket.setVoyageId(resultSet.getString("Voyage_flightNumber"));
                ticket.setSeatClass(resultSet.getString("Seat_number"));
                ticket.setPassengerId(resultSet.getInt("Passenger_id"));
                ticket.setSeatNumber(resultSet.getString("Seat_number"));
                list.add(ticket);
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

    public TicketEntity getById (int id){
        TicketEntity ticket=null;
        ResultSet resultSet=null;
        try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM ticket WHERE ID = ?")){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ticket = new TicketEntity();
                ticket.setId(resultSet.getInt("ID"));
                ticket.setVoyageId(resultSet.getString("Voyage_flightNumber"));
                ticket.setSeatClass(resultSet.getString("Class"));
                ticket.setPassengerId(resultSet.getInt("Passenger_id"));
                ticket.setSeatNumber(resultSet.getString("Seat_number"));
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
        return ticket;
    }



    public  void save(TicketEntity entity){
        try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO ticket VALUES (?,?,?,?,?)")){
            statement.setInt(1,entity.getId());
            statement.setString(2,entity.getVoyageId());
            statement.setString(3,entity.getSeatClass());
            statement.setInt(4,entity.getPassengerId());
            statement.setString(5,entity.getSeatNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void update(TicketEntity entity){
        try  (PreparedStatement statement = getConnection().prepareStatement("UPDATE ticket SET Voyage_flightNumber=?," +
                "Class = ?, Passenger_id = ?, Seat_number = ? WHERE ID = ?")){
            statement.setString(1,entity.getVoyageId());
            statement.setString(2,entity.getSeatClass());
            statement.setInt(3,entity.getPassengerId());
            statement.setString(4,entity.getSeatNumber());
            statement.setInt(5,entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

