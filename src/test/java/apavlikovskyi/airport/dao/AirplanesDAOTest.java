package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.AirplanesEntity;
import apavlikovskyi.airport.entity.VoyageEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Артем on 08.04.2017.
 */
public class AirplanesDAOTest {
    AirplanesDAO airplanesDAO;
    VoyageDAO voyageDAO;

    @Before
    public void before(){
        DataBaseConnection.migrate();
        airplanesDAO =new AirplanesDAO();
        voyageDAO=new VoyageDAO();
        voyageDAO.save(new VoyageEntity(1,"KT430","Munchen","Venecia"));
        airplanesDAO.save(new AirplanesEntity("KT430","TU-134",250));
    }

    @After
    public void after() throws SQLException {
        PreparedStatement ps=DataBaseConnection.getConnection().prepareStatement("DROP DATABASE testairport");
        ps.executeUpdate();
        ps.close();
        DataBaseConnection.closeConnection();
    }

    @Test
    public void getById(){
        AirplanesEntity airplanesEntity=airplanesDAO.getById(1L);
        assertEquals(airplanesEntity.getFlightNumber(),"KT430");
    }


    @Test
    public void delete(){
        airplanesDAO.deleteById(1L);
        AirplanesEntity arrivalEntity= airplanesDAO.getById(1L);
        assertNull(arrivalEntity);
    }
    @Test
    public void update(){
        airplanesDAO.update(new AirplanesEntity("KT430","TU-154",250));
        assertEquals("TU-154",airplanesDAO.getById(1L).getModel());
    }

    @Test
    public void save(){
        assertEquals(airplanesDAO.getById(1L).getModel(),"TU-134");
    }

    @Test
    public void getAll(){
        assertNotNull(airplanesDAO.getAll());
    }
}
