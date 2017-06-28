package apavlikovskyi.airport.servlets.Airplanes;

import apavlikovskyi.airport.dao.AirplanesDAO;
import apavlikovskyi.airport.entity.AirplanesEntity;

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
 * Created by Diana P on 04.06.2017.
 */
@WebServlet("/edit_airplanes_table")
public class EditAirplanesTable extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String flight_number =  request.getParameter("flight_number");
            String model = request.getParameter("model");
            int seatsCapacity = Integer.parseInt(request.getParameter("seats_capacity"));
            AirplanesEntity airplanesEntity = new AirplanesEntity(flight_number,model,seatsCapacity);
            AirplanesDAO airplanesDAO= new AirplanesDAO();
            airplanesDAO.update(airplanesEntity);

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
            out.println("<h3>Airplanes table</h3>");
            out.println("<table class='table_blur'>");
            out.println("<tr>");
            out.println("<td><strong>Flight Number</strong></td>");
            out.println("<td><strong>Model-name</strong></td>");
            out.println("<td><strong>Seats capacity</strong></td>");
            out.println("</tr>");
            AirplanesDAO airplanesDAO = new AirplanesDAO();
            List<AirplanesEntity> list= airplanesDAO.getAll();
            list.sort(Comparator.comparing(AirplanesEntity::getModel));
            for(Iterator iterator = list.iterator(); iterator.hasNext();){
                AirplanesEntity airplanes= (AirplanesEntity)iterator.next();
                out.println("<form class='myForm' method='post' action='edit_airplanes_table'>");
                out.println("<tr>");
                out.println("<td><input type='text' name='flight_number' value=" + "'" + airplanes.getFlightNumber()+ "'" +"></td>");
                out.println("<td><input type='text' name='model' value='" + airplanes.getModel()+"'"+"></td>");
                out.println("<td><input type='text' name='seats_capacity' value='" + airplanes.getSeats_capacity()+"'"+"></td>");
                out.println("<td><input class='button' type='submit' value='Применить изменения для ID'"  +  airplanes.getModel()+ "'></td>");
                out.println("</tr>");
                out.println("</form>");
            }
            out.println("</table>");
            out.println("<p><a href='add_new_airplane_row'>Добавить новую строку</a></p>");
            out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
            out.println("</body>");
            out.print("</html>");
            out.close();
        }
}


