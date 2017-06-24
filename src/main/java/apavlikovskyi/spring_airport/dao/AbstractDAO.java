package apavlikovskyi.spring_airport.dao;

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
        ResultSet resultSet=null;
        try(PreparedStatement statement=getConnection().prepareStatement("SELECT * FROM " + getTableName())) {
            resultSet=statement.executeQuery();
            list = new ArrayList<>();
            getResultSetAll(list,resultSet);
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

    protected abstract String getTableName();
    protected abstract List<T> getResultSetAll(List<T> list, ResultSet resultSet);
    protected abstract T getResultSet(ResultSet resultSet);
}
