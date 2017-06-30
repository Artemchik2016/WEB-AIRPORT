package apavlikovskyi.airport.servlets.passengers;
import apavlikovskyi.airport.dao.PassengersDAO;
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
* Created by Diana P on 30.06.2017.
*/
@WebServlet("/add_new_passenger")
public class AddNewPassenger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name =  request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String nationality = request.getParameter("last_name");
        String passport = request.getParameter("passport");
        String dob = request.getParameter("dob");
        String sex = request.getParameter("sex");
        PassengersEntity passengersEntity = new PassengersEntity(first_name,last_name,nationality,passport,dob,sex);
        PassengersDAO passengersDAO= new PassengersDAO();
        passengersDAO.save(passengersEntity);
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
        out.println("<h3>Passengers table</h3>");
        out.print("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Nationality</th>");
        out.println("<th>Passport</th>");
        out.println("<th>DOB</th>");
        out.println("<th>Sex</th>");
        out.println("</tr>");
        PassengersDAO passengersDAO = new PassengersDAO();
        List<PassengersEntity> list = passengersDAO.getAll();
        list.sort(Comparator.comparing(PassengersEntity::getLast_name));
        for(Iterator iterator = list.iterator(); iterator.hasNext();){
            PassengersEntity passengersEntity= (PassengersEntity) iterator.next();
            out.println("<tr>");
            out.println("<td><input type='text' name='id' value=" + "'" + passengersEntity.getId()+ "'" +"></td>");
            out.println("<td><input type='text' name='first_name' value=" + "'" + passengersEntity.getFirst_name()+ "'" +"></td>");
            out.println("<td><input type='text' name='last_name' value='" + passengersEntity.getLast_name()+"'"+"></td>");
            out.println("<td><input type='text' name='nationality' value='" + passengersEntity.getNationality()+"'"+"></td>");
            out.println("<td><input type='text' name='passport' value='" + passengersEntity.getPassport()+"'"+"></td>");
            out.println("<td><input type='text' name='dob' value='" + passengersEntity.getDob()+"'"+"></td>");
            out.println("<td><input type='text' name='sex' value='" + passengersEntity.getSex()+"'"+"></td>");
            out.println("<td><input class='button' type='submit' value='Применить изменения для ID'"  +  passengersEntity.getId()+ "'></td>");
            out.println("</tr>");
        }
        out.println("<form class='myForm' method='post' action='/add_new_passenger'>");
        out.println("<tr>");
        out.println("<td><input type='text' name='id'></td>");
        out.println("<td><input type='text' name='first_name'></td>");
        out.println("<td><input type='text' name='last_name'></td>");
        out.println("<td><input type='text' name='nationality'></td>");
        out.println("<td><input type='text' name='passport'></td>");
        out.println("<td><input type='text' name='dob'></td>");
        out.println("<td><input type='text' name='sex'></td>");
        out.println("<td><input class='button' type='submit' value='Применить изменения для ID'></td>");
        out.println("</tr>");
        out.println("</form>");
        out.println("</table>");
        out.println("<p><a href='/add_new_passenger'>Добавить новую строку</a></p>");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("</body>");
        out.print("</html>");
        out.close();
    }
}




