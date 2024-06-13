import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Daily_Report")
public class Daily_Report extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            // Output HTML structure
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"ISO-8859-1\">");
            out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
            out.print("<title>Home Page</title>");
            // CSS styles
            out.print("<style>");
            out.print("/* Internal CSS */");
            out.print("body {");
            out.print("  background-color: #f2f2f2;");
            out.print("}");
           
            out.print(".slide-box {");
            out.print("  position: absolute;");
            out.print("  left: 15%;");
            out.print("  width: 1000px;");
            out.print("  height: 300px;");
            out.print("  background-color: #fff;");
            out.print("  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);");
            out.print("  border-radius: 25px; /* Added border radius */");
            out.print("  padding: 20px;");
            out.print("}");
            out.print(".label {");
            out.print("  font-weight: bold;");
            out.print("  margin-bottom: 10px;");
            out.print("}");
            out.print(".h {");
            out.print("  text-align: center;");
            out.print("  margin-bottom: 35px;");
            out.print("}");
            out.print("h3 {");
            out.print("  margin-bottom: 35px;");
            out.print("}");
            out.print("</style>");
//            out.print("<style>");
//            
////            out.print(".slide-box { overflow: hidden; }");
////            out.print(".slide-box-item { float: left; width: 50%; }");
//            out.print("</style>");
            out.print("</head>");
            out.print("<body>");
            
            // Header
            out.print("<h1>WELCOME <br> TO TRAFFIC ACCIDENT MANAGEMENT SYSTEM</h1>");

            // Include Navigation
            request.getRequestDispatcher("Navigation").include(request, response);

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/traffic_accident_information_management_system", "root",
                    "root");

            // Retrieve data from the database
            PreparedStatement stmt = conn.prepareStatement("SELECT Location, Description, Date FROM reports");
            ResultSet rs = stmt.executeQuery();

            // Output div boxes with accident information
            int count = 0;
            while (rs.next()) {
                if (count % 3 == 0) {
                    out.print("<div class=\"slide-box\">");
                }
                out.print("<div class=\"slide-box-item\">");
                out.print("<h2>Accident</h2>");
                out.print("<ul>");
                out.print("<li class=\"label\">Location: " + rs.getString("Location") + "</li>");
                out.print("<li class=\"label\">Date: " + rs.getString("Date") + "</li>");
                out.print("<li class=\"label\">Description: " + rs.getString("Description") + "</li>");
                out.print("</ul>");

                out.print("</div>");
                count++;
                if (count % 3 == 0) {
                    out.print("</div>");
                }
            }

            // Close the last slide-box if not closed
            if (count % 3 != 0) {
                out.print("</div>");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

            // Close HTML tags
            out.print("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward GET requests to service method
        service(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward POST requests to service method
        service(request, response);
    }
}
