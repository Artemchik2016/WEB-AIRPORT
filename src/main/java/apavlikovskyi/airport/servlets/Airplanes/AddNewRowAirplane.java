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
 * Created by Diana P on 14.06.2017.
 */
@WebServlet("/add_new_airplane_row")
public class AddNewRowAirplane extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flight_number =  request.getParameter("flight_number");
        String model = request.getParameter("model");
        int seatsCapacity = Integer.parseInt(request.getParameter("seatsCapacity"));
        AirplanesEntity airplanesEntity = new AirplanesEntity(flight_number,model,seatsCapacity);
        AirplanesDAO airplanesDAO= new AirplanesDAO();
        airplanesDAO.save(airplanesEntity);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js\"></script> \n" +
                "    <script src=\"http://malsup.github.com/jquery.form.js\"></script> ");
        out.println("<script src='ajax.js'></script>");
        out.println("<title>Airport</title>");
        out.println("<link rel='stylesheet' type='text/css' href='style2.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Airplanes table</h3>");
        out.println("<table class='table_blur'>");
        out.println("<tr>");
        out.println("<th>Flight Number</th>");
        out.println("<th>Model</th>");
        out.println("<th>Seats Capacity</th>");
        out.println("<tr>");
        AirplanesDAO airplanesDAO=new AirplanesDAO();
        List<AirplanesEntity> list= airplanesDAO.getAll();
        list.sort(Comparator.comparing(AirplanesEntity::getModel));
        for(Iterator it = list.iterator(); it.hasNext();){
            AirplanesEntity airplanesEntity= (AirplanesEntity) it.next();
            out.println("<tr>");
            out.println("<td><input type='text' name='flight_number' value='" + airplanesEntity.getFlightNumber()+"'"+"></td>");
            out.println("<td><input type='text' name='model' value='" + airplanesEntity.getModel()+"'"+"></td>");
            out.println("<td><input type='text' name='seatsCapacity' value='" + airplanesEntity.getSeats_capacity()+"'"+"></td>");
            out.println("</tr>");
        }
        out.println("<form class='myForm' method='post' action='add_new_airplane_row'>");
        out.println("<tr>");
        out.println("<td><input type='text' name='flight_number'></td>");
        out.println("<td><input type='text' name='model'></td>");
        out.println("<td><input type='text' name='seatsCapacity'></td>");
        out.println("<td><input class='button' type='submit' value='Применить изменения для ID'></td>");
        out.println("</tr>");
        out.println("</form>");
        out.println("</table>");
        out.println("<p><a href='add_new_airplane_row'>Добавить строку</a></p>");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("</body>");
        out.print("</html>");
        out.close();
    }
}
