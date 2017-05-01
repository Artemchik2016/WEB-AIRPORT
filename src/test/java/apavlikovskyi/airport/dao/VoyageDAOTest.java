package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.VoyageEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.Assert.*;

/**
 * Created by Артем on 08.04.2017.
 */
public class VoyageDAOTest {
    private VoyageDAO voyageDAO;
    PreparedStatement statement;

    @Before
    public void before(){
        DataBaseConnection.migrate();
        voyageDAO=new VoyageDAO();
        voyageDAO.save(new VoyageEntity(1,"TK-3452","Frankfurt","Santos"));
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
        voyageDAO.deleteById(1);
        VoyageEntity voyageEntity= voyageDAO.getById(1);
        assertNull(voyageEntity);
    }


   @Test
   public void getById(){
        VoyageEntity voyageEntity=voyageDAO.getById(1);
        assertEquals(voyageEntity.getFlightNumber(),"TK-3452");
   }


    @Test
    public void update(){
        voyageDAO.update(new VoyageEntity(1,"TK4000","Odessa","Kyiv"));
        assertEquals("TK4000",voyageDAO.getById(1).getFlightNumber());
    }

    @Test
    public void save(){
        assertEquals(voyageDAO.getById(1).getArrivalPort(),"Frankfurt");
    }

    @Test
    public void getAll(){
        assertNotNull(voyageDAO.getAll());
    }
}
