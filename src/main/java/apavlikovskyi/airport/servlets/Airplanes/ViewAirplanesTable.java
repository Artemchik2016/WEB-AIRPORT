package apavlikovskyi.airport.servlets.Airplanes;

import apavlikovskyi.airport.dao.AirplanesDAO;
import apavlikovskyi.airport.dao.VoyageDAO;
import apavlikovskyi.airport.entity.AirplanesEntity;
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
 * Created by Diana P on 04.06.2017.
 */
@WebServlet("/airplanes_table")
public class ViewAirplanesTable extends HttpServlet {
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
        out.println("<h3>Voyage table</h3>");
        out.print("<table class='table_blur'>");
        out.println("<tr>");
        out.println("<td><strong>Voyage</strong></td>");
        out.println("<td><strong>Model-Airplane</strong></td>");
        out.println("<td><strong>Seats capacity</strong></td>");
        out.println("</tr>");
        AirplanesDAO airplanesDAO=new AirplanesDAO();
        List<AirplanesEntity> list = airplanesDAO.getAll();
        for(Iterator it = list.iterator(); it.hasNext();){
            AirplanesEntity airplanesEntity= (AirplanesEntity)it.next();
            out.println("<tr>");
            out.println("<td>" + airplanesEntity.getVoyage_flightNumber()+"</td>");
            out.println("<td>" + airplanesEntity.getName() +"</td>");
            out.println("<td>" + airplanesEntity.getSeats_capacity() + "</td>");
            out.println("</tr>");
        }
        out.println("</table");
        out.println("<body>");
        out.println("</html>");
        out.close();
    }
}

