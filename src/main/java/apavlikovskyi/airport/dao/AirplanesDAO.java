package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.entity.AirplanesEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Артем on 08.04.2017.
 */
public class AirplanesDAO extends AbstractDAO<AirplanesEntity> {

    public  void save(AirplanesEntity airplanesEntity) {
        statementExecute(airplanesEntity, "INSERT INTO airplanes VALUES (?,?,?)");
    }

    public  void update(AirplanesEntity airplanesEntity){
        statementExecute(airplanesEntity,"UPDATE airplanes SET " +
                "Name = ?, Seats_capacity = ? WHERE Voyage_flightNumber = ?");
    }


    @Override
    protected String getTableName() {
        return "airplanes";
    }

    @Override
    protected AirplanesEntity getResultSet(ResultSet resultSet) {
        AirplanesEntity airplanesEntity = new AirplanesEntity();
        try {
            while (resultSet.next()) {
                airplanesEntity.setVoyage_flightNumber(resultSet.getString("Voyage_flightNumber"));
                airplanesEntity.setName(resultSet.getString("Name"));
                airplanesEntity.setSeats_capacity(resultSet.getInt("Seats_capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airplanesEntity;
    }

    @Override
    protected List<AirplanesEntity> getResultSetAll(List list, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                AirplanesEntity airplanesEntity = new AirplanesEntity();
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

    void statementExecute(AirplanesEntity airplanesEntity, String sql){
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1,airplanesEntity.getVoyage_flightNumber());
            statement.setString(2,airplanesEntity.getModel());
            statement.setInt(3,airplanesEntity.getSeats_capacity());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


