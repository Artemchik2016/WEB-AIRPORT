package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.DepartureEntity;
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
public class DepartureDAOTest {

        private DepartureDAO departureDAO;
        private VoyageDAO voyageDAO;

        @Before
        public void before(){
            DataBaseConnection.migrate();
            departureDAO=new DepartureDAO();
            voyageDAO=new VoyageDAO();
            voyageDAO.save(new VoyageEntity(1,"KT4390","Munchen","Venecia"));
            departureDAO.save(new DepartureEntity("KT4390","25.23.12","16:40","A","stopped","A32"));

        }

        @After
        public void after() throws SQLException {
           try (PreparedStatement ps=DataBaseConnection.getConnection().prepareStatement("DROP DATABASE testairport")) {
               ps.executeUpdate();
               DataBaseConnection.closeConnection();
           }

        }


        @Test
        public void delete(){
            departureDAO.deleteById("KT4390");
            DepartureEntity departureEntity= departureDAO.getById("KT4390");
            assertNull(departureEntity);
        }


        @Test
        public void getById(){
            DepartureEntity departureEntity=departureDAO.getById("KT4390");
            assertEquals(departureEntity.getFlight_status(),"stopped");
        }


        @Test
        public void update(){
            departureDAO.update(new DepartureEntity("KT4450","25.23.12"
                    ,"16:40","A","stopped","A32"));
            assertEquals("25.23.12",departureDAO.getById("KT4390").getDate());
            assertEquals("A",departureDAO.getById("KT4390").getTerminal());
        }

        @Test
        public void save(){
            assertEquals(departureDAO.getById("KT4390").getVoyage_id(),"KT4390");
        }

        @Test
        public void getAll(){
            assertNotNull(departureDAO.getAll());
        }
    }


