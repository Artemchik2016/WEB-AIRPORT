package apavlikovskyi.airport.servlets.Tickets;

import apavlikovskyi.airport.dao.VoyageDAO;
import apavlikovskyi.airport.entity.VoyageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Артем on 05.05.2017.
 */
@WebServlet("/edit_ticket_table")
public class EditTicketTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String flight_number =  request.getParameter("flight_number");
       String arrivalPort = request.getParameter("arrival_port");
       String departurePort = request.getParameter("departure_port");
       int id = Integer.parseInt(request.getParameter("ID"));
       VoyageEntity voyageEntity = new VoyageEntity(id ,flight_number,arrivalPort,departurePort);
       VoyageDAO voyageDAO= new VoyageDAO();
       voyageDAO.update(voyageEntity);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML>");
        out.println("<html>");
        out.println("<head>");
        out.println(" <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js\"></script> \n" +
                "    <script src=\"http://malsup.github.com/jquery.form.js\"></script> ");
        out.println("<script src='ajax.js'></script>");
        out.println("<title>Airport</title>");
        out.println("<link rel='stylesheet' type='text/css' href='style2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Voyage table</h3>");
        out.println("<table class='table_blur'>");
        out.println("<tr>");
        out.println("<td><strong>ID</strong></td>");
        out.println("<td><strong>Flight Number</strong></td>");
        out.println("<td><strong>Arrival port</strong></td>");
        out.println("<td><strong>Departure port</strong></td>");
        out.println("</tr>");
        VoyageDAO voyageDAO=new VoyageDAO();
        List<VoyageEntity> list= voyageDAO.getAll();
        list.sort(Comparator.comparingInt(VoyageEntity::getId));
        for(Iterator it = list.iterator(); it.hasNext();){
            VoyageEntity voyageEntity= (VoyageEntity) it.next();
            out.println("<form class='myForm' method='post' action='edit_voyage_table'>");
            out.println("<tr>");
            out.println("<td><input type='text' name='voyage_id' value=" + "'" + voyageEntity.getId()+ "'" +"></td>");
            out.println("<td><input type='text' name='flight_number' value='" + voyageEntity.getFlightNumber()+"'"+"></td>");
            out.println("<td><input type='text' name='arrival_port' value='" + voyageEntity.getArrivalPort()+"'"+"></td>");
            out.println("<td><input type='text' name='departure_port' value='" + voyageEntity.getDeparturePort()+"'"+"></td>");
            out.println("<td><input class='button' type='submit' value='Применить изменения для ID "+ voyageEntity.getId()+ "'></td>");
            out.println("</tr>");
            out.println("</form>");
        }
        out.println("</table>");
        out.println("<p><a href='add_new_row'>Добавить новую строку</a></p>");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("</body>");
        out.print("</html>");
        out.close();
    }
}
