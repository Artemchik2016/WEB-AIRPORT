package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.ArrivalEntity;
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
public class ArrivalDAOTest {

        private ArrivalDAO arrivalDAO;
        private VoyageDAO voyageDAO;

        @Before
        public void before(){
            DataBaseConnection.migrate();
            arrivalDAO =new ArrivalDAO();
            voyageDAO=new VoyageDAO();
            voyageDAO.save(new VoyageEntity(1,"KT4390","Munchen","Venecia"));
            arrivalDAO.save(new ArrivalEntity("KT4390","25.23.12","16:40","A","stopped","A32"));

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
            arrivalDAO.deleteById("KT4390");
            ArrivalEntity arrivalEntity= arrivalDAO.getById("KT4390");
            assertNull(arrivalEntity);
        }


        @Test
        public void getById(){
            ArrivalEntity departureEntity= arrivalDAO.getById("KT4390");
            assertEquals(departureEntity.getFlight_status(),"stopped");
        }


        @Test
        public void update(){
            arrivalDAO.update(new ArrivalEntity("KT4450","25.23.12"
                    ,"16:40","A","stopped","A32"));
            assertEquals("25.23.12", arrivalDAO.getById("KT4390").getDate());
            assertEquals("A", arrivalDAO.getById("KT4390").getTerminal());
        }

        @Test
        public void save(){
            assertEquals(arrivalDAO.getById("KT4390").getVoyage_id(),"KT4390");
        }

        @Test
        public void getAll(){
            assertNotNull(arrivalDAO.getAll());
        }
    }

