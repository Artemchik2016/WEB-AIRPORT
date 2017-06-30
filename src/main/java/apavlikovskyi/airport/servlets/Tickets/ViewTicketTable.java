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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Артем on 03.05.2017.
 */
@WebServlet("/view_ticket_table")
public class ViewTicketTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   

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
        out.println("<title>Airport</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Tickets table</h3>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Flight Number</th>");
        out.println("<th>Arrival port</th>");
        out.println("<th>Departure port</th>");
        out.println("</tr>");
        VoyageDAO voyageDAO=new VoyageDAO();
        List<VoyageEntity> list= voyageDAO.getAll();
        for(Iterator it= list.iterator(); it.hasNext();){
            VoyageEntity voyageEntity= (VoyageEntity) it.next();
            out.println("<tr>");
            out.println("<td>" + voyageEntity.getFlightNumber()+"</td>");
            out.println("<td>" + voyageEntity.getArrivalPort() +"</td>");
            out.println("<td>" + voyageEntity.getDeparturePort() + "</td>");
            out.println("</tr>");
        }
        out.println("</table");
        out.println("<body>");
        out.println("</html>");
        out.close();
    }
}
