package apavlikovskyi.airport.servlets.arrival;

import apavlikovskyi.airport.dao.ArrivalDAO;
import apavlikovskyi.airport.dao.PassengersDAO;
import apavlikovskyi.airport.entity.ArrivalEntity;
import apavlikovskyi.airport.entity.PassengersEntity;

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
 * Created by Diana P on 01.07.2017.
 */
@WebServlet("/edit_arrival_table")
public class EditArrival extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voyage = request.getParameter("voyage");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String terminal = request.getParameter("terminal");
        String flight_status = request.getParameter("flight_status");
        String gate = request.getParameter("gate");
        ArrivalEntity arrivalEntity = new ArrivalEntity(voyage,date,time,terminal,flight_status,gate);
        ArrivalDAO arrivalDAO = new ArrivalDAO();
        arrivalDAO.update(arrivalEntity);
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
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Passengers table</h3>");
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
        list.sort(Comparator.comparing(ArrivalEntity::getTime));
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            ArrivalEntity arrivalEntity = (ArrivalEntity) iterator.next();
            out.println("<form class='myForm' method='post' action='edit_arrival_table'>");
            out.println("<tr>");
            out.println("<td><input type='text' name='voyage' value=" + "'" + arrivalEntity.getVoyage_id() + "'" + "></td>");
            out.println("<td><input type='text' name='date' value=" + "'" + arrivalEntity.getDate() + "'" + "></td>");
            out.println("<td><input type='text' name='time' value='" + arrivalEntity.getTime() + "'" + "></td>");
            out.println("<td><input type='text' name='terminal' value='" + arrivalEntity.getTerminal() + "'" + "></td>");
            out.println("<td><input type='text' name='flight_status' value='" + arrivalEntity.getFlight_status() + "'" + "></td>");
            out.println("<td><input type='text' name='gate' value='" + arrivalEntity.getGate() + "'" + "></td>");
            out.println("<td><input class='button' type='submit' value='Применить изменения для ID'" + arrivalEntity.getFlight_status() + "'></td>");
            out.println("</tr>");
            out.println("</form>");
        }
        out.println("</table>");
        out.println("<p><a href='/add_new_arrival_row'>Добавить новую строку</a></p>");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("</body>");
        out.print("</html>");
        out.close();
    }
}
