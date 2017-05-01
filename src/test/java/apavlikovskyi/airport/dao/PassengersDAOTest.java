package apavlikovskyi.airport.dao;
import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.PassengersEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.Assert.*;

/**
 * Created by Артем on 08.04.2017.
 */
public class PassengersDAOTest {
    private PassengersDAO passengersDAO;

    @Before
    public void before(){
        DataBaseConnection.migrate();
        passengersDAO=new PassengersDAO();
        passengersDAO.save(new PassengersEntity(1,"Lakosta","Adelina",
                "ukraine","EE800447","25.23.2003","male"));
    }


    @After
    public void after() throws SQLException {
        PreparedStatement ps=DataBaseConnection.getConnection().prepareStatement("DROP DATABASE testairport");
        ps.executeUpdate();
        ps.close();
        DataBaseConnection.closeConnection();
    }



    @Test
    public void delete(){
        passengersDAO.deleteById(1);
        PassengersEntity passengersEntity= passengersDAO.getById(1);
        assertNull(passengersEntity);
    }


    @Test
    public void getById(){
        PassengersEntity passengersEntity=passengersDAO.getById(1);
        assertEquals(passengersEntity.getPassport(),"EE800447");
    }


    @Test
    public void update(){
        passengersDAO.update(new PassengersEntity(1,"Lakosta","Kalaka",
                "Latvian","AA800447","25.23.2003","male"));
        assertEquals("Latvian",passengersDAO.getById(1).getNationality());
        assertEquals("Kalaka",passengersDAO.getById(1).getLast_name());
    }

    @Test
    public void save(){
        assertEquals(passengersDAO.getById(1).getPassport(),"EE800447");
    }

    @Test
    public void getAll(){
        assertNotNull(passengersDAO.getAll());
    }
}



