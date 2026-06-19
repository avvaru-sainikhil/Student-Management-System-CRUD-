import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
        	
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "830976");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM stud"); 

            out.println("<h3>Student Records</h3>");
            out.println("<table border='1' ><tr><th>Name</th><th>Age</th><th>Department</th></tr>");
            
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("name") + 
                            "</td><td>" + rs.getInt("age") + "</td><td>" + rs.getString("department") + "</td></tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } 
    }
}