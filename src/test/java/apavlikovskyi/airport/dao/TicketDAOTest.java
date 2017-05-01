package apavlikovskyi.airport.dao;

import apavlikovskyi.airport.dao.daoUtil.DataBaseConnection;
import apavlikovskyi.airport.entity.PassengersEntity;
import apavlikovskyi.airport.entity.TicketEntity;
import apavlikovskyi.airport.entity.VoyageEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Diana P on 06.04.2017.
 */
public class TicketDAOTest {
    private TicketDAO ticketDAO;
    private PassengersDAO passengersDAO;
    private VoyageDAO voyageDAO;


    @Before
    public void before(){
        DataBaseConnection.migrate();
        passengersDAO=new PassengersDAO();
        voyageDAO=new VoyageDAO();

        passengersDAO.save(new PassengersEntity(1,"Artem","Pavlikovskyi",
                "ukranian","ee80047","09/07/1990","male"));
        voyageDAO.save(new VoyageEntity(1,"KT4390","Munchen","Venecia"));
        voyageDAO.save(new VoyageEntity(2,"KT4380","Austria","Vena"));

        ticketDAO= new TicketDAO();

        ticketDAO.save(new TicketEntity(1,"KT4390",
                "business",1,
                "B23"));
    }

    @After
    public void after() throws SQLException {
        PreparedStatement ps= DataBaseConnection.getConnection().prepareStatement("DROP DATABASE testairport");
        ps.executeUpdate();
        ps.close();
        DataBaseConnection.closeConnection();
    }


    @Test
    public void delete() throws Exception {
        ticketDAO.deleteById(1);
        TicketEntity ticketEntity=ticketDAO.getById(1);
        assertNull(ticketEntity);
    }

    @Test
    public void getAll() throws Exception {
        assertNotNull(ticketDAO.getAll());
    }

    @Test
    public void getById() throws Exception {
     TicketEntity ticketEntity=ticketDAO.getById(1);
     assertEquals("KT4390",ticketEntity.getVoyageId());
    }

    @Test
    public void update() throws Exception {
        TicketEntity ticketEntity=new TicketEntity(1,"KT4380","econom",1,"B23");
        ticketDAO.update(ticketEntity);
        assertEquals(ticketDAO.getById(1),ticketEntity);
        assertEquals(ticketEntity.getVoyageId(),ticketDAO.getById(1).getVoyageId());

    }

    @Test
    public void save() throws Exception {
       TicketEntity ticketEntity=ticketDAO.getById(1);
       assertEquals("B23",ticketEntity.getSeatNumber());
       assertEquals(1,ticketEntity.getPassengerId());
    }
}