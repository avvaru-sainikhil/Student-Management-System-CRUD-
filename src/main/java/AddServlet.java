import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");

        Connection c = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "830976");
            Statement stmt=c.createStatement();
            //stmt.executeUpdate("drop table stud");
            //stmt.executeUpdate("create table stud(name varchar(20),age number(20),department varchar(5))");
            

            ps = c.prepareStatement("INSERT INTO stud (name, age, department) VALUES (?, ?, ?)");

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
            	out.print("<center>");
                out.println("<h3>Student added successfully!</h3>");
                out.print("</center>");
            } else {
            	out.print("<center>");
                out.println("<h3>Failed to add student.</h3>");
                out.print("</center>");
            }
        } catch (Exception e) {
        	out.print("<center>");
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
            out.print("</center>");
        } 
    }
}