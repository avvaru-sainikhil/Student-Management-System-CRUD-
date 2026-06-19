import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "830976");

            ps = conn.prepareStatement("DELETE FROM stud WHERE name = ?");
            ps.setString(1, name);
            int result = ps.executeUpdate();

            if (result > 0) {
            	out.print("<center>");
                out.println("<h3>Student Deleted Successfully!</h3>");
                out.print("</center>");
            } else {
            	out.print("<center>");
                out.println("<h3>Student Not Found!</h3>");
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