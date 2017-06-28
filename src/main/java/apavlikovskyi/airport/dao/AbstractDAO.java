package apavlikovskyi.airport.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static apavlikovskyi.airport.dao.daoUtil.DataBaseConnection.getConnection;

/**
 * Created by Diana P on 24.06.2017.
 */
public abstract class AbstractDAO<T> {

    public T getById (Long id) {
        T entity = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM " + getTableName() + " WHERE ID = ?")) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity = entityFromResult(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void deleteById(Long id){
        try(PreparedStatement statement=getConnection()
                .prepareStatement("DELETE FROM " + getTableName() + " WHERE ID = ?")) {
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<T> getAll(){
        List<T> list=null;
        ResultSet resultset=null;
        try(PreparedStatement statement=getConnection().prepareStatement("SELECT * FROM " + getTableName())) {
            resultset=statement.executeQuery();
            list = new ArrayList<>();
            while (resultset.next()) {
                list.add(entityFromResult(resultset));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    protected abstract String getTableName();
    protected abstract T entityFromResult(ResultSet resultSet) throws SQLException;
}
