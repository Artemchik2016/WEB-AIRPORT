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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Diana P on 30.06.2017.
 */
@WebServlet("/passengers_table")
public class ViewPassengers extends HttpServlet {
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
        for(Iterator it = list.iterator(); it.hasNext();){
            PassengersEntity passengersEntity= (PassengersEntity) it.next();
            out.println("<tr>");
            out.println("<td>" + passengersEntity.getId()+"</td>");
            out.println("<td>" + passengersEntity.getFirst_name() +"</td>");
            out.println("<td>" + passengersEntity.getLast_name() + "</td>");
            out.println("<td>" + passengersEntity.getNationality() + "</td>");
            out.println("<td>" + passengersEntity.getPassport() + "</td>");
            out.println("<td>" + passengersEntity.getDob() + "</td>");
            out.println("<td>" + passengersEntity.getSex() + "</td>");
            out.println("</tr>");
        }
        out.println("</table");
        out.println("<p><a href='http://localhost:8080/'>Вернуться на главную страницу</a></p>");
        out.println("<body>");
        out.println("</html>");
        out.close();
    }
}

