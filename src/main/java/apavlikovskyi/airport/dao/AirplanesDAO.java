package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.AirplanesEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class AirplanesDAO extends AbstractDAO<AirplanesEntity> {

    public void save(AirplanesEntity airplanesEntity) {
        statementExecute(airplanesEntity, "INSERT INTO airplanes VALUES (null,?,?,?)");
    }

    public void update(AirplanesEntity airplanesEntity) {
        try (PreparedStatement statement = getConnection().prepareStatement("UPDATE airplanes SET " +
                "Name = ?, Seats_capacity = ? WHERE Voyage_flightNumber = ?")) {
            statement.setString(1, airplanesEntity.getModel());
            statement.setInt(2, airplanesEntity.getSeats_capacity());
            statement.setString(3, airplanesEntity.getFlightNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        @Override
        protected String getTableName () {
            return "airplanes";
        }

        @Override
        protected AirplanesEntity entityFromResult (ResultSet resultSet) throws SQLException {
            AirplanesEntity airplanesEntity = new AirplanesEntity();
            airplanesEntity.setVoyage_flightNumber(resultSet.getString("Voyage_flightNumber"));
            airplanesEntity.setName(resultSet.getString("Name"));
            airplanesEntity.setSeats_capacity(resultSet.getInt("Seats_capacity"));
            return airplanesEntity;
        }

    void statementExecute(AirplanesEntity airplanesEntity, String sql) {
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, airplanesEntity.getFlightNumber());
            statement.setString(2, airplanesEntity.getModel());
            statement.setInt(3, airplanesEntity.getSeats_capacity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


