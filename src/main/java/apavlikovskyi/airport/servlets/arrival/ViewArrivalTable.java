package apavlikovskyi.airport.servlets.arrival;

import apavlikovskyi.airport.dao.ArrivalDAO;
import apavlikovskyi.airport.entity.ArrivalEntity;
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
 * Created by Diana P on 01.07.2017.
 */
@WebServlet("/arrival_table")
public class ViewArrivalTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        out.println("<h3>Arrival table</h3>");
        out.print("<table>");
        out.println("<tr>");
        out.println("<th>Voyage flight</th>");
        out.println("<th>Date</th>");
        out.println("<th>Time</th>");
        out.println("<th>Terminal</th>");
        out.println("<th>Flight_status</th>");
        out.println("<th>Gate</th>");
        out.println("</tr>");
        ArrivalDAO arrivalDAO = new ArrivalDAO();
        List<ArrivalEntity> list = arrivalDAO.getAll();
        for(Iterator it = list.iterator(); it.hasNext();){
            ArrivalEntity arrivalEntity= (ArrivalEntity) it.next();
            out.println("<tr>");
            out.println("<td>" + arrivalEntity.getVoyage_id()+"</td>");
            out.println("<td>" + arrivalEntity.getDate() +"</td>");
            out.println("<td>" + arrivalEntity.getTime() + "</td>");
            out.println("<td>" + arrivalEntity.getTerminal() + "</td>");
            out.println("<td>" + arrivalEntity.getFlight_status() + "</td>");
            out.println("<td>" + arrivalEntity.getGate() + "</td>");
            out.println("</tr>");
        }
        out.println("</table");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("<body>");
        out.println("</html>");
        out.close();
    }
}

