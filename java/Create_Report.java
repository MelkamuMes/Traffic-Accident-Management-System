import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Create_Report")
public class Create_Report extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Create_Report() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.print("<html>");
        out.print("<head>");
        out.print("<meta charset=\"ISO-8859-1\">");
        out.print("<link rel=\"stylesheet\" href=\"new_nav.css\">");
        out.print("<title>Home Page</title>");
        out.println("<script>");
        out.println("function showPopup() {");
        out.println("alert(\"Report is Registered Successfully!\");");
        out.println("}");
        out.println("</script>");
        out.print("</head>");
        out.print("<body >");
        out.print("<div class= 'Login-button'><input value=\"Logout\" type=\"submit\"> </div>");
        out.print("<div class= 'navigation'>");
        out.print("<nav>");
        out.print("<ul>");
        out.print("<li><a href=\"Admin_Home_Page\"> <img src=\"img/home.png\"></a></li>");
        out.print("<li><a href=\"Home.java\"><img src=\"img/contact.png\"></a></li>");
        out.print("<li><a href=\"Home.java\"><img src=\"img/about.png\"></a></li>");
        out.print("<li><a href=\"Home.java\"><img src=\"img/emergency.png\"></a></li>");
        out.print("</ul>");
        out.print("</nav>");
        out.print("</div>");
        out.print("<div class = 'title'><h1>Admin Officer Page</h1></div>");
        out.print("<div class = 'Admin_Pic' ><img src= \"img/Admin.jpg\"></div>");
        out.print("<div class = 'Atitle'><p>Create Accident Summary Report</p></div><div></div>");
        out.print("<div id = 'RForm'>");

        out.print("<form action=\"Create_Report\" method=\"post\">");

        String status = request.getParameter("Description");
        String date = request.getParameter("Date");
        String location = request.getParameter("Location");

        out.print("<label>Date:</label><br>");
        out.print("<input type=\"text\" name=\"Date\" size=\"20\" value=\"" + date + "\" /><br>");
        out.print("<label>Location:</label><br>");
        out.print("<input type=\"text\" name=\"Location\" size=\"30\" value=\"" + location + "\"  /><br>");
        out.print("<label>Description:</label><br>");
        out.print("<div class=\"Des\">");
        out.print("<input type=\"text\" name=\"Description\" value=\"" + status + "\" /><br>");
        out.print("</div>");
        out.print("<input type=\"submit\" name=\"submit\" onclick=\"showPopup()\" value=\"Register\" />");
        out.print("</form>");

        out.print("</div>");

        String submit = request.getParameter("submit");
        if (submit != null && submit.equals("Register")) {
            String driverName = "com.mysql.cj.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";
            String dbUsername = "root";
            String dbPassword = "root";

            try {
                Class.forName(driverName);
                Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

                String sql = "INSERT INTO reports(Location, Description, Date) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setString(1, location);
                ps.setString(2, status);
                ps.setString(3, date);
                 ps.executeUpdate();

                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        out.print("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

     
    }

}