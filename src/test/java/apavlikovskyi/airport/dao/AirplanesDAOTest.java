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
        voyageDAO.save(new VoyageEntity(1,"KT4390","Munchen","Venecia"));
        airplanesDAO.save(new AirplanesEntity("KT4390","TU-134",250));
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
        AirplanesEntity airplanesEntity=airplanesDAO.getById("KT4390");
        assertEquals(airplanesEntity.getVoyage_flightNumber(),"KT4390");
    }


    @Test
    public void delete(){
        airplanesDAO.deleteById("KT4390");
        AirplanesEntity arrivalEntity= airplanesDAO.getById("KT4390");
        assertNull(arrivalEntity);
    }
    @Test
    public void update(){
        airplanesDAO.update(new AirplanesEntity("KT4390","TU-154",250));
        assertEquals("TU-154",airplanesDAO.getById("KT4390").getName());
    }



    @Test
    public void save(){
        assertEquals(airplanesDAO.getById("KT4390").getName(),"TU-134");
    }

    @Test
    public void getAll(){
        assertNotNull(airplanesDAO.getAll());
    }
}
